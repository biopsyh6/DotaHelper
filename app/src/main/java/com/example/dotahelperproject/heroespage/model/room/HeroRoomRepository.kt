package com.example.dotahelperproject.heroespage.model.room

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.domain.abstractions.hero.HeroRepository
import com.example.domain.entities.Hero
import com.example.dotahelperproject.MainActivity
import com.google.firebase.database.DatabaseReference

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
    override fun getHeroesByAttributeFirebaseId(attributeId: LiveData<String?>): LiveData<List<Hero>> {
        TODO("Not yet implemented")
    }

    override fun getHeroByName(name: String): LiveData<Hero?> {
        TODO("Not yet implemented")
    }

    override fun getHeroReferenceByName(name: String): DatabaseReference {
        TODO("Not yet implemented")
    }


//    override fun getHeroByName(name: String): LiveData<Hero?> {
//        TODO("Not yet implemented")
//    }


//    override fun getHeroesByAttributeFirebaseId(attributeId: LiveData<String?>): LiveData<List<Hero>> {
//        TODO("Not yet implemented")
//    }

//    override fun getHeroesByAttributeId(attributeId: Int) = heroDao.getHeroesByAttributeId(attributeId)

    //    override fun getHeroesByRoleId(roleId: Int) = heroDao.getHeroesByRoleId(roleId)
    override suspend fun create(hero: Hero, onSuccess: () -> Unit) {
        TODO("Not yet implemented")
    }

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