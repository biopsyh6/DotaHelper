package com.example.application.HeroUseCases.firebase

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.abstractions.hero.HeroRepository
import com.example.domain.entities.Hero
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AppFirebaseHeroRepository: HeroRepository {
    private val mAuth = FirebaseAuth.getInstance()
    private val database = FirebaseDatabase.getInstance("https://dotahelperproject-default-rtdb.europe-west1.firebasedatabase.app/")
        .reference.child(mAuth.currentUser?.uid.toString())

    override fun saveHero(hero: Hero) {
        val id = database.child("heroes").push().key
        if(id != null) {
            hero.firebaseId = id
            val heroData = hero.copy()
            database.child("heroes").child(id).setValue(heroData)
        }
    }

    override fun getAllHeroes(): LiveData<List<Hero>> = AllHeroesLiveData()

    override fun clearAllHeroes() {
        database.child("heroes").removeValue()
    }

    override fun getHeroById(id: Int): LiveData<Hero> {
        TODO("Not yet implemented")
    }

    override fun getHeroesByAttributeFirebaseId(attributeId: LiveData<String?>): LiveData<List<Hero>> {
        val heroesLiveData = MutableLiveData<List<Hero>>()
        database.child("heroes").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val heroes = snapshot.children.mapNotNull { it.getValue(Hero::class.java) }
                Log.d("CHECK", heroes.toString())
                heroesLiveData.value = heroes.filter { it.attributeId == attributeId.value }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
        return heroesLiveData
    }

    override fun getHeroByName(name: String): LiveData<Hero?> {
        val heroesLiveData = getAllHeroes()
        val heroLiveData = MutableLiveData<Hero?>()
        heroesLiveData.observeForever {
            heroes ->
            val hero = heroes.find { it.name == name }
            heroLiveData.value = hero
        }
        return heroLiveData
    }

    override fun getHeroReferenceByName(name: String): DatabaseReference {
        return database.child("heroes").child(name)
    }

//    override fun getHeroesByRoleId(roleId: Int): LiveData<List<Hero>> {
//        TODO("Not yet implemented")
//    }

    override suspend fun create(hero: Hero, onSuccess: () -> Unit) {
        val heroId = database.child("heroes").push().key
        if(heroId != null) {
            hero.firebaseId = heroId
            database.child("heroes").child(heroId).setValue(hero).addOnSuccessListener {
                onSuccess()
            }
        }
    }
}