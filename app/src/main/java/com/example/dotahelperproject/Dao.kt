package com.example.dotahelperproject

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.domain.entities.Attribute
import com.example.domain.entities.Hero
import com.example.domain.entities.Role
import com.example.domain.entities.Skill
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Insert
    fun insertHero(item: com.example.domain.entities.Hero)
    @Insert(entity = com.example.domain.entities.Attribute::class, onConflict = OnConflictStrategy.IGNORE)
    fun insertAttribute(item: com.example.domain.entities.Attribute)
    @Insert
    fun insertRole(item: com.example.domain.entities.Role)
    @Insert
    fun insertSkill(item: com.example.domain.entities.Skill)
    @Query("SELECT * FROM heroes")
    fun getAllHeroes(): Flow<List<com.example.domain.entities.Hero>>
    @Query("DELETE FROM attributes WHERE name='Strength'")
    fun deleteStrengthAttribute(): Int
    @Query("SELECT COUNT(*) FROM attributes WHERE name = :name")
    fun getAttributeCountByName(name: String): Int
}