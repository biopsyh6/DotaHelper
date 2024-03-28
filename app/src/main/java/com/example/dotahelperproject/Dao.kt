package com.example.dotahelperproject

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dotahelperproject.entities.Attribute
import com.example.dotahelperproject.entities.Hero
import com.example.dotahelperproject.entities.Role
import com.example.dotahelperproject.entities.Skill
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Insert
    fun insertHero(item: Hero)
    @Insert(entity = Attribute::class, onConflict = OnConflictStrategy.IGNORE)
    fun insertAttribute(item: Attribute)
    @Insert
    fun insertRole(item: Role)
    @Insert
    fun insertSkill(item: Skill)
    @Query("SELECT * FROM heroes")
    fun getAllHeroes(): Flow<List<Hero>>
    @Query("DELETE FROM attributes WHERE name='Strength'")
    fun deleteStrengthAttribute(): Int
    @Query("SELECT COUNT(*) FROM attributes WHERE name = :name")
    fun getAttributeCountByName(name: String): Int
}