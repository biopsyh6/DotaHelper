package com.example.dotahelperproject.heroespage.model.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dotahelperproject.entities.Hero

@Dao
interface HeroDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(hero: Hero)

    @Delete
    fun clearHeroes(vararg hero: Hero)

    @Query("SELECT * FROM heroes ORDER BY name ASC")
    fun getAllHeroes(): LiveData<List<Hero>>

    @Query("SELECT * FROM heroes WHERE heroId =:id")
    fun getHeroById(id: Int): LiveData<Hero>

    @Query("SELECT * FROM heroes WHERE attributeId =:attributeId")
    fun getHeroesByAttributeId(attributeId: Int) : LiveData<List<Hero>>

    @Query("SELECT * FROM heroes WHERE roleId =:roleId")
    fun getHeroesByRoleId(roleId: Int) : LiveData<List<Hero>>
}