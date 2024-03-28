package com.example.dotahelperproject.skillspage.model

import androidx.lifecycle.LiveData
import com.example.dotahelperproject.entities.Skill

interface SkillRepository {
    fun saveSkill(skill: Skill)
    fun getAllSkills(): LiveData<List<Skill>>
    fun clearAllSkills()
    fun getSkillById(id: Int): LiveData<Skill>
    fun getSkillsByHeroId(heroId: Int) : LiveData<List<Skill>>
}