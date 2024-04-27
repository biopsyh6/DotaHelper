package com.example.dotahelperproject.runespage.model.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.domain.entities.Rune

@Dao
interface RuneDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(rune: com.example.domain.entities.Rune)

    @Delete
    fun clearRunes(vararg rune: com.example.domain.entities.Rune)

    @Query("SELECT * FROM runes ORDER BY name ASC")
    fun getAllRunes(): LiveData<List<com.example.domain.entities.Rune>>

    @Query("SELECT * FROM runes WHERE runeId =:id")
    fun getRuneById(id: Int): LiveData<com.example.domain.entities.Rune>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(runes: List<com.example.domain.entities.Rune>)
}