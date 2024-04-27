package com.example.dotahelperproject.skillspage.model.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.domain.entities.Skill

@Dao
interface SkillDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(skill: com.example.domain.entities.Skill)

    @Delete
    fun clearSkills(vararg skill: com.example.domain.entities.Skill)

    @Query("SELECT * FROM skills ORDER BY name ASC")
    fun getAllSkills(): LiveData<List<com.example.domain.entities.Skill>>

    @Query("SELECT * FROM skills WHERE skillId =:id")
    fun getSkillById(id: Int): LiveData<com.example.domain.entities.Skill>

    @Query("SELECT * FROM skills WHERE heroId =:heroId")
    fun getSkillsByHeroId(heroId: Int): LiveData<List<com.example.domain.entities.Skill>>
}