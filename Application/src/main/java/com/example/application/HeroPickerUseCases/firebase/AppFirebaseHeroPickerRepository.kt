package com.example.application.HeroPickerUseCases.firebase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.abstractions.hero.HeroPickerRepository
import com.example.domain.entities.HeroPicker
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AppFirebaseHeroPickerRepository: HeroPickerRepository {
    private val mAuth = FirebaseAuth.getInstance()
    private val database = FirebaseDatabase.getInstance("https://dotahelperproject-default-rtdb.europe-west1.firebasedatabase.app/")
        .reference.child(mAuth.currentUser?.uid.toString())

    override fun saveHero(hero: HeroPicker) {
        val id = database.child("heroespicker").push().key
        if (id != null) {
            hero.firebaseId = id
            val heroData = hero.copy()
            database.child("heroespicker").child(id).setValue(heroData)
        }
    }

    override fun getAllHeroes(): LiveData<List<HeroPicker>> = AllHeroesPickerLiveData()

    override fun clearAllHeroes() {
        database.child("heroespicker").removeValue()
    }

    override fun getHeroById(id: Int): LiveData<HeroPicker> {
        val heroData = MutableLiveData<HeroPicker>()

        database.child("heroespicker").orderByChild("id").equalTo(id.toDouble())
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (heroSnapshot in snapshot.children) {
                        val hero = heroSnapshot.getValue(HeroPicker::class.java)
                        if (hero != null) {
                            heroData.value = hero!!
                            break
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
        return heroData
    }

    override fun getHeroes(): LiveData<List<HeroPicker>> {
        val heroesLiveData = MutableLiveData<List<HeroPicker>>()
        database.child("heroespicker").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val heroes = snapshot.children.mapNotNull { it.getValue(HeroPicker::class.java) }
                heroesLiveData.value = heroes
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
        return heroesLiveData
    }

    override fun getHeroByName(name: String): LiveData<HeroPicker?> {
        val heroesLiveData = getHeroes()
        val heroLiveData = MutableLiveData<HeroPicker?>()
        heroesLiveData.observeForever {
            heroes ->
            val hero = heroes.find { it.name == name }
            heroLiveData.value = hero
        }
        return heroLiveData
    }

}