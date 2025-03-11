package com.example.application.ItemUseCases.firebase

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.domain.entities.Item
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class AllItemsLiveData: LiveData<List<Item>>() {
    private val mAuth = FirebaseAuth.getInstance()
    private val database = Firebase.database.reference
        .child(mAuth.currentUser?.uid.toString())

    private val listener = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            val items = mutableListOf<Item>()
            snapshot.children.map {
                items.add(it.getValue(Item::class.java) ?: Item())
            }
            value = items
            Log.d("All items livedata", "get data")
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