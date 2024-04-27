package com.example.domain.abstractions.rune

import androidx.lifecycle.LiveData
import com.example.domain.entities.Rune

interface RuneRepository {
    fun saveRune(rune: Rune)
    fun saveRunes(runes: List<Rune>)
    fun getAllRunes(): LiveData<List<Rune>>
    fun clearAllRunes()
    fun getRuneById(id: Int): LiveData<Rune>
    suspend fun create(rune: Rune, onSuccess: () -> Unit)
}