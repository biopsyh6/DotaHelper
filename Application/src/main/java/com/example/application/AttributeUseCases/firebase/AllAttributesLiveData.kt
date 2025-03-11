package com.example.application.AttributeUseCases.firebase

import androidx.lifecycle.LiveData
import com.example.domain.entities.Attribute
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class AllAttributesLiveData: LiveData<List<Attribute>>() {
    private val mAuth = FirebaseAuth.getInstance()
    private val database = Firebase.database.reference
        .child(mAuth.currentUser?.uid.toString())
//    private val database = FirebaseDatabase.getInstance("https://dotahelperproject-default-rtdb.europe-west1.firebasedatabase.app/")
//    .reference.child(mAuth.currentUser?.uid.toString()).child("attributes")

    private val listener = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            val attributes = mutableListOf<Attribute>()
            snapshot.children.map {
                attributes.add(it.getValue(Attribute::class.java) ?: Attribute())
            }
//            snapshot.children.forEach {
//                val attribute = it.getValue(Attribute::class.java)
//                if (attribute != null) {
//                    attributes.add(attribute)
//                }
//            }
            value = attributes
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