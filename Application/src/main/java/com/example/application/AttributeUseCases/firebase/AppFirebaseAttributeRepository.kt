package com.example.application.AttributeUseCases.firebase

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.abstractions.attribute.AttributeRepository
import com.example.domain.entities.Attribute
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AppFirebaseAttributeRepository: AttributeRepository {
    private val mAuth = FirebaseAuth.getInstance()
    private val database = FirebaseDatabase.getInstance("https://dotahelperproject-default-rtdb.europe-west1.firebasedatabase.app/")
        .reference.child(mAuth.currentUser?.uid.toString())

    override fun saveAttribute(attribute: Attribute) {
        val id = database.child("attributes").push().key
        if(id != null){
            attribute.firebaseId = id
            database.child("attributes").child(id).setValue(attribute)
        }
    }

    override fun saveAttributes(attributes: List<Attribute>) {
        attributes.forEach {saveAttribute(it)}
    }

//    override fun getAllAttributes(): LiveData<List<Attribute>> = AllAttributesLiveData()

    override fun clearAllAttributes() {
        database.child("attributes").removeValue()
    }

    override fun getAttributeById(id: Int): MutableLiveData<Attribute?> {
        val attributeLiveData = MutableLiveData<Attribute?>()
        database.child("attributes").child(id.toString()).addValueEventListener(object :
        ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val attribute = snapshot.getValue(Attribute::class.java)
                attributeLiveData.value = attribute
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
        return attributeLiveData
    }

    override suspend fun create(attribute: Attribute, onSuccess: () -> Unit) {
        val attributeId = database.child("attributes").push().key
        if(attributeId != null){
            attribute.firebaseId = attributeId
            database.child("attributes").child(attributeId).setValue(attribute).addOnSuccessListener {
                onSuccess()
            }
        }
    }

    override fun getAttributeFirebaseIdByName(name: String): LiveData<String?> {
        val firebaseIdLiveData = MutableLiveData<String?>()
        getAllAttributes().observeForever { attributes ->
            Log.d("MEOW", attributes.toString())
            val firebaseId = attributes?.find { it.name == name }?.firebaseId
            firebaseIdLiveData.value = firebaseId
        }
        return firebaseIdLiveData
    }

    override fun getAllAttributes(): MutableLiveData<List<Attribute>> {
        val attributesLiveData = MutableLiveData<List<Attribute>>()
        database.child("attributes").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                val attributes = snapshot.children.mapNotNull { it.getValue(Attribute::class.java) }
                Log.d("CHOPA", attributes.toString())
                attributesLiveData.value = attributes
                Log.d("CHECK", attributesLiveData.value.toString())
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
        return attributesLiveData
    }


}