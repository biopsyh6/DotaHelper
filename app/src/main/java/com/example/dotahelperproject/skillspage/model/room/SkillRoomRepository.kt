package com.example.dotahelperproject.skillspage.model.room

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.domain.abstractions.skill.SkillRepository
import com.example.domain.entities.Skill
import com.example.dotahelperproject.MainActivity

class SkillRoomRepository: SkillRepository {
    private val skillDao: SkillDao = MainActivity.database.skillDao()
    private val allSkills: LiveData<List<com.example.domain.entities.Skill>>

    init {
        allSkills = skillDao.getAllSkills()
    }
    private class InsertAsyncTask internal constructor(private val dao: SkillDao):
        AsyncTask<com.example.domain.entities.Skill, Void, Void>() {
        override fun doInBackground(vararg params: com.example.domain.entities.Skill): Void? {
            dao.insert(params[0])
            return null
        }
    }
    private class DeleteAsyncTask internal constructor(private val dao: SkillDao):
        AsyncTask<com.example.domain.entities.Skill, Void, Void>() {
        override fun doInBackground(vararg params: com.example.domain.entities.Skill): Void?{
            dao.clearSkills(*params)
            return null
        }
    }

    override fun saveSkill(skill: com.example.domain.entities.Skill) {
        InsertAsyncTask(skillDao).execute(skill)
    }

    override fun getAllSkills() = allSkills

    override fun clearAllSkills() {
        val skillArray = allSkills.value?.toTypedArray()
        if(skillArray != null){
            DeleteAsyncTask(skillDao).execute(*skillArray)
        }
    }

    override fun getSkillById(id: Int) = skillDao.getSkillById(id)
    override fun getSkillsByHeroId(heroId: String): LiveData<List<Skill>> {
        TODO("Not yet implemented")
    }

    override suspend fun create(skill: Skill, onSuccess: () -> Unit) {
        TODO("Not yet implemented")
    }
}