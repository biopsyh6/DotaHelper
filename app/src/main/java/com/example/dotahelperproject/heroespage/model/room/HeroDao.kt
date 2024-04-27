package com.example.dotahelperproject.heroespage.model.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.domain.entities.Hero

@Dao
interface HeroDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(hero: com.example.domain.entities.Hero)

    @Delete
    fun clearHeroes(vararg hero: com.example.domain.entities.Hero)

    @Query("SELECT * FROM heroes ORDER BY name ASC")
    fun getAllHeroes(): LiveData<List<com.example.domain.entities.Hero>>

    @Query("SELECT * FROM heroes WHERE heroId =:id")
    fun getHeroById(id: Int): LiveData<com.example.domain.entities.Hero>

    @Query("SELECT * FROM heroes WHERE attributeId =:attributeId")
    fun getHeroesByAttributeId(attributeId: Int) : LiveData<List<com.example.domain.entities.Hero>>

    @Query("SELECT * FROM heroes WHERE roleId =:roleId")
    fun getHeroesByRoleId(roleId: Int) : LiveData<List<com.example.domain.entities.Hero>>
}