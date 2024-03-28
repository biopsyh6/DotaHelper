package com.example.dotahelperproject.runespage.model

import androidx.lifecycle.LiveData
import com.example.dotahelperproject.entities.Rune

interface RuneRepository {
    fun saveRune(rune: Rune)
    fun getAllRunes(): LiveData<List<Rune>>
    fun clearAllRunes()
    fun getRuneById(id: Int): LiveData<Rune>
}