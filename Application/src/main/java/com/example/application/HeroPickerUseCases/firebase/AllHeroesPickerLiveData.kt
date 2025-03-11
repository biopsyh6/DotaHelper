package com.example.application.HeroPickerUseCases.firebase

import androidx.lifecycle.LiveData
import com.example.domain.entities.HeroPicker
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class AllHeroesPickerLiveData: LiveData<List<HeroPicker>>() {
    private val mAuth = FirebaseAuth.getInstance()
    private val database = Firebase.database.reference
        .child(mAuth.currentUser?.uid.toString())

    private val listener = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            val heroes = mutableListOf<HeroPicker>()
            snapshot.children.map {
                heroes.add(it.getValue(HeroPicker::class.java) ?: HeroPicker())
            }
            value = heroes
        }

        override fun onCancelled(error: DatabaseError) {
            TODO("Not yet implemented")
        }

    }
    override fun onActive() {
        database.addValueEventListener(listener)
        super.onActive()
    }

    override fun onInactive() {
        database.removeEventListener(listener)
        super.onInactive()
    }
}