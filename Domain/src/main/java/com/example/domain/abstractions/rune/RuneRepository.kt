package com.example.domain.abstractions.rune

import androidx.lifecycle.LiveData
import com.example.domain.entities.Rune

interface RuneRepository {
    fun saveRune(rune: com.example.domain.entities.Rune)
    fun saveRunes(runes: List<com.example.domain.entities.Rune>)
    fun getAllRunes(): LiveData<List<com.example.domain.entities.Rune>>
    fun clearAllRunes()
    fun getRuneById(id: Int): LiveData<com.example.domain.entities.Rune>
    suspend fun create(rune: com.example.domain.entities.Rune, onSuccess: () -> Unit)
}