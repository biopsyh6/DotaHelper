package com.example.dotahelperproject.neutralspage.model.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.domain.entities.Neutral

@Dao
interface NeutralDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(neutral: com.example.domain.entities.Neutral)

    @Delete
    fun clearNeutrals(vararg neutral: com.example.domain.entities.Neutral)

    @Query("SELECT * FROM neutrals ORDER BY name ASC")
    fun getAllNeutrals(): LiveData<List<com.example.domain.entities.Neutral>>

    @Query("SELECT * FROM neutrals WHERE neutralId =:id")
    fun getNeutralById(id: Int): LiveData<com.example.domain.entities.Neutral>
}