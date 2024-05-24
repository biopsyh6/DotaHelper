package com.example.domain.abstractions.skill

import androidx.lifecycle.LiveData
import com.example.domain.entities.Skill

interface SkillRepository {
    fun saveSkill(skill: Skill)
    fun getAllSkills(): LiveData<List<Skill>>
    fun clearAllSkills()
    fun getSkillById(id: Int): LiveData<Skill>
    fun getSkillsByHeroId(heroId: String) : LiveData<List<Skill>>
    suspend fun create(skill: Skill, onSuccess: () -> Unit)
}