package com.example.domain.abstractions.neutral

import androidx.lifecycle.LiveData
import com.example.domain.entities.Neutral
interface NeutralRepository {
    fun saveNeutral(neutral: com.example.domain.entities.Neutral)
    fun getAllNeutrals(): LiveData<List<com.example.domain.entities.Neutral>>
    fun clearAllNeutrals()
    fun getNeutralById(id: Int): LiveData<com.example.domain.entities.Neutral>
}