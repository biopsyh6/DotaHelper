package com.example.dotahelperproject.runespage.model.room

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.dotahelperproject.MainActivity
import com.example.dotahelperproject.entities.Neutral
import com.example.dotahelperproject.entities.Rune
import com.example.dotahelperproject.neutralspage.model.room.NeutralDao
import com.example.dotahelperproject.runespage.model.RuneRepository

class RuneRoomRepository: RuneRepository {
    private val runeDao: RuneDao = MainActivity.database.runeDao()
    private val allRunes: LiveData<List<Rune>>

    init {
        allRunes = runeDao.getAllRunes()
    }

    private class InsertAsyncTask internal constructor(private val dao: RuneDao):
        AsyncTask<Rune, Void, Void>() {
        override fun doInBackground(vararg params: Rune): Void? {
            dao.insert(params[0])
            return null
        }
    }
    private class DeleteAsyncTask internal constructor(private val dao: RuneDao):
        AsyncTask<Rune, Void, Void>() {
        override fun doInBackground(vararg params: Rune): Void?{
            dao.clearRunes(*params)
            return null
        }
    }

    override fun saveRune(rune: Rune) {
        InsertAsyncTask(runeDao).execute(rune)
    }

    override fun getAllRunes() = allRunes

    override fun clearAllRunes() {
        val runeArray = allRunes.value?.toTypedArray()
        if(runeArray != null){
            DeleteAsyncTask(runeDao).execute(*runeArray)
        }
    }

    override fun getRuneById(id: Int) = runeDao.getRuneById(id)
}