package com.example.dotahelperproject.heroespage.model.room

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.dotahelperproject.MainActivity
import com.example.dotahelperproject.MainDb
import com.example.dotahelperproject.entities.Hero
import com.example.dotahelperproject.heroespage.model.HeroRepository
import com.example.dotahelperproject.heroespage.presenter.HeroespagePresenter
import org.jetbrains.annotations.Async

class HeroRoomRepository : HeroRepository {
    private val heroDao: HeroDao = MainActivity.database.heroDao()
    private val allHeroes: LiveData<List<Hero>>

    init {
        allHeroes = heroDao.getAllHeroes()
    }

    override fun saveHero(hero: Hero) {
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
        AsyncTask<Hero, Void, Void>() {
        override fun doInBackground(vararg params: Hero): Void?{
            dao.insert(params[0])
            return null
        }
    }
    private class DeleteAsyncTask internal constructor(private val dao: HeroDao) :
        AsyncTask<Hero, Void, Void>(){
            override fun doInBackground(vararg params: Hero): Void?{
                dao.clearHeroes(*params)
                return null
            }
        }
}