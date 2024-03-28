package com.example.dotahelperproject.skillspage.model.room

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.dotahelperproject.MainActivity
import com.example.dotahelperproject.entities.Neutral
import com.example.dotahelperproject.entities.Skill
import com.example.dotahelperproject.neutralspage.model.room.NeutralDao
import com.example.dotahelperproject.skillspage.model.SkillRepository

class SkillRoomRepository: SkillRepository {
    private val skillDao: SkillDao = MainActivity.database.skillDao()
    private val allSkills: LiveData<List<Skill>>

    init {
        allSkills = skillDao.getAllSkills()
    }
    private class InsertAsyncTask internal constructor(private val dao: SkillDao):
        AsyncTask<Skill, Void, Void>() {
        override fun doInBackground(vararg params: Skill): Void? {
            dao.insert(params[0])
            return null
        }
    }
    private class DeleteAsyncTask internal constructor(private val dao: SkillDao):
        AsyncTask<Skill, Void, Void>() {
        override fun doInBackground(vararg params: Skill): Void?{
            dao.clearSkills(*params)
            return null
        }
    }

    override fun saveSkill(skill: Skill) {
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

    override fun getSkillsByHeroId(heroId: Int) = skillDao.getSkillsByHeroId(heroId)
}