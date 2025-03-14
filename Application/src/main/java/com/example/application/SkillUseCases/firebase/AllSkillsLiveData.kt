package com.example.application.SkillUseCases.firebase

import androidx.lifecycle.LiveData
import com.example.domain.entities.Skill
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class AllSkillsLiveData: LiveData<List<Skill>>() {
    private val mAuth = FirebaseAuth.getInstance()
    private val database = Firebase.database.reference
        .child(mAuth.currentUser?.uid.toString())

    private val listener = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            val skills = mutableListOf<Skill>()
            snapshot.children.map {
                skills.add(it.getValue(Skill::class.java) ?: Skill())
            }
            value = skills
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