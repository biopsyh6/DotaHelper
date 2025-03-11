package com.example.application.ItemUseCases.firebase

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.abstractions.item.ItemRepository
import com.example.domain.entities.Item
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AppFirebaseItemRepository: ItemRepository {
    private val mAuth = FirebaseAuth.getInstance()
    private val database = FirebaseDatabase.getInstance("https://dotahelperproject-default-rtdb.europe-west1.firebasedatabase.app/")
        .reference.child(mAuth.currentUser?.uid.toString())

    override fun saveItem(item: Item) {
        val id = database.child("items").push().key
        if(id != null){
            item.firebaseId = id
            database.child("items").child(id).setValue(item)
        }
    }

    override fun getAllItems(): LiveData<List<Item>> = AllItemsLiveData()

    override fun clearAllItems() {
        database.child("items").removeValue()
    }

    override fun getItemById(id: Int): LiveData<Item> {
        TODO("Not yet implemented")
    }

    override fun getItemByCategoryId(categoryId: Int): LiveData<List<Item>> {
        TODO("Not yet implemented")
    }

    override fun getItems(): LiveData<List<Item>> {
        val itemsLiveData = MutableLiveData<List<Item>>()
        database.child("items").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val items = snapshot.children.mapNotNull { it.getValue(Item::class.java) }
                Log.d("getItems method", items.toString())
                itemsLiveData.value = items
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
        return itemsLiveData
    }

    override fun getItemsByTypeName(typeName: String): LiveData<List<Item>> {
        val itemsLiveData = MutableLiveData<List<Item>>()
        database.child("items").orderByChild("type").equalTo(typeName)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val items = snapshot.children.mapNotNull { it.getValue(Item::class.java) }
                    itemsLiveData.value = items
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
        return itemsLiveData
    }


    override suspend fun create(item: Item, onSuccess: () -> Unit) {
        val itemId = database.child("items").push().key
        if(itemId != null){
            item.firebaseId = itemId
            database.child("items").child(itemId).setValue(item).addOnSuccessListener {
                onSuccess()
            }
        }
    }

}