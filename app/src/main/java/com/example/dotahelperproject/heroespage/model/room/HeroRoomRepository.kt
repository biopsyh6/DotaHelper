package com.example.dotahelperproject.heroespage.model.room

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.dotahelperproject.MainActivity
import com.example.domain.abstractions.hero.HeroRepository

class HeroRoomRepository : HeroRepository {
    private val heroDao: HeroDao = MainActivity.database.heroDao()
    private val allHeroes: LiveData<List<com.example.domain.entities.Hero>>

    init {
        allHeroes = heroDao.getAllHeroes()
    }

    override fun saveHero(hero: com.example.domain.entities.Hero) {
        InsertAsyncTask(heroDao).execute(hero)
    }

    override fun getAllHeroes() = allHeroes

    override fun clearAllHeroes() {
       val heroArray = allHeroes.value?.toTypedArray()
        if (heroArray != null){
            DeleteAsyncTask(heroDao).execute(*heroArray)
        }
    }

    override fun getHeroById(id: Int) = heroDao.getHeroById(id)
    override fun getHeroesByAttributeId(attributeId: Int) = heroDao.getHeroesByAttributeId(attributeId)
    override fun getHeroesByRoleId(roleId: Int) = heroDao.getHeroesByRoleId(roleId)

    private class InsertAsyncTask internal constructor(private val dao: HeroDao) :
        AsyncTask<com.example.domain.entities.Hero, Void, Void>() {
        override fun doInBackground(vararg params: com.example.domain.entities.Hero): Void?{
            dao.insert(params[0])
            return null
        }
    }
    private class DeleteAsyncTask internal constructor(private val dao: HeroDao) :
        AsyncTask<com.example.domain.entities.Hero, Void, Void>(){
            override fun doInBackground(vararg params: com.example.domain.entities.Hero): Void?{
                dao.clearHeroes(*params)
                return null
            }
        }
}