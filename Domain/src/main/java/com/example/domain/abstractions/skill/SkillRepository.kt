package com.example.domain.abstractions.skill

import androidx.lifecycle.LiveData
import com.example.domain.entities.Skill

interface SkillRepository {
    fun saveSkill(skill: com.example.domain.entities.Skill)
    fun getAllSkills(): LiveData<List<com.example.domain.entities.Skill>>
    fun clearAllSkills()
    fun getSkillById(id: Int): LiveData<com.example.domain.entities.Skill>
    fun getSkillsByHeroId(heroId: Int) : LiveData<List<com.example.domain.entities.Skill>>
}