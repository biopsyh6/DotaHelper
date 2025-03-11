package com.example.application.RuneUseCases.firebase

import androidx.lifecycle.LiveData
import com.example.domain.entities.Rune
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class AllRunesLiveData: LiveData<List<com.example.domain.entities.Rune>>() {
    private val mAuth = FirebaseAuth.getInstance()
    private val database = Firebase.database.reference
        .child(mAuth.currentUser?.uid.toString())

    private val listener = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            val runes = mutableListOf<com.example.domain.entities.Rune>()
            snapshot.children.map {
                runes.add(it.getValue(com.example.domain.entities.Rune::class.java) ?: com.example.domain.entities.Rune())
            }
            value = runes
        }

        override fun onCancelled(error: DatabaseError) {}

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