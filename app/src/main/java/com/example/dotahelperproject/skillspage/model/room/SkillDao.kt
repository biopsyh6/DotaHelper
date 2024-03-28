package com.example.dotahelperproject.skillspage.model.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dotahelperproject.entities.Skill

@Dao
interface SkillDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(skill: Skill)

    @Delete
    fun clearSkills(vararg skill: Skill)

    @Query("SELECT * FROM skills ORDER BY name ASC")
    fun getAllSkills(): LiveData<List<Skill>>

    @Query("SELECT * FROM skills WHERE skillId =:id")
    fun getSkillById(id: Int): LiveData<Skill>

    @Query("SELECT * FROM skills WHERE heroId =:heroId")
    fun getSkillsByHeroId(heroId: Int): LiveData<List<Skill>>
}