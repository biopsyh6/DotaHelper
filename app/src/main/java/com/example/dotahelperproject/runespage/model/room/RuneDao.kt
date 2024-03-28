package com.example.dotahelperproject.runespage.model.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dotahelperproject.entities.Rune

@Dao
interface RuneDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(rune: Rune)

    @Delete
    fun clearRunes(vararg rune: Rune)

    @Query("SELECT * FROM runes ORDER BY name ASC")
    fun getAllRunes(): LiveData<List<Rune>>

    @Query("SELECT * FROM runes WHERE runeId =:id")
    fun getRuneById(id: Int): LiveData<Rune>
}