package com.example.dotahelperproject.neutralspage.model.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dotahelperproject.entities.Neutral

@Dao
interface NeutralDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(neutral: Neutral)

    @Delete
    fun clearNeutrals(vararg neutral: Neutral)

    @Query("SELECT * FROM neutrals ORDER BY name ASC")
    fun getAllNeutrals(): LiveData<List<Neutral>>

    @Query("SELECT * FROM neutrals WHERE neutralId =:id")
    fun getNeutralById(id: Int): LiveData<Neutral>
}