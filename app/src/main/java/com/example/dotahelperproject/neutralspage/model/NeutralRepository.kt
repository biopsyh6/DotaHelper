package com.example.dotahelperproject.neutralspage.model

import androidx.lifecycle.LiveData
import com.example.dotahelperproject.entities.Neutral

interface NeutralRepository {
    fun saveNeutral(neutral: Neutral)
    fun getAllNeutrals(): LiveData<List<Neutral>>
    fun clearAllNeutrals()
    fun getNeutralById(id: Int): LiveData<Neutral>
}