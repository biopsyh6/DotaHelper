package com.example.application.RuneUseCases.firebase

import androidx.lifecycle.LiveData
import com.example.domain.abstractions.rune.RuneRepository
import com.example.domain.entities.Rune
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class AppFirebaseRuneRepository: RuneRepository {
    private val mAuth = FirebaseAuth.getInstance()  //Получение текущего авторизованного пользователя
    private val database = FirebaseDatabase.getInstance("https://dotahelperproject-default-rtdb.europe-west1.firebasedatabase.app/")
        .reference.child(mAuth.currentUser?.uid.toString())

    override fun saveRune(rune: Rune) {
        val id = database.child("runes").push().key
        if(id != null){
            rune.firebaseId = id
            database.child("runes").child(id).setValue(rune)
        }
    }

    override fun saveRunes(runes: List<Rune>) {
        runes.forEach {saveRune(it)}
    }

    override fun getAllRunes(): LiveData<List<Rune>> = AllRunesLiveData()

    override fun clearAllRunes() {
        database.child("runes").removeValue()
    }

    override fun getRuneById(id: Int): LiveData<Rune> {
        TODO("Not yet implemented")
    }

    override suspend fun create(rune: Rune, onSuccess: () -> Unit) {
        val runeId = database.child("runes").push().key
        if(runeId != null) {
            rune.firebaseId = runeId
            database.child("runes").child(runeId).setValue(rune).addOnSuccessListener {
                onSuccess()
            }
        }
    }
}