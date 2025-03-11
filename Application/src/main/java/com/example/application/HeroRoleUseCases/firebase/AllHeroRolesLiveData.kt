package com.example.application.HeroRoleUseCases.firebase

import androidx.lifecycle.LiveData
import com.example.domain.entities.HeroRole
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class AllHeroRolesLiveData: LiveData<List<HeroRole>>() {
    private val mAuth = FirebaseAuth.getInstance()
    private val database = Firebase.database.reference
        .child(mAuth.currentUser?.uid.toString())

    private val listener = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            val heroRoles = mutableListOf<HeroRole>()
            snapshot.children.map {
                heroRoles.add(it.getValue(HeroRole::class.java) ?: HeroRole())
            }
            value = heroRoles
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