package com.example.dotahelperproject.runespage.model.room

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.dotahelperproject.MainActivity
import com.example.domain.abstractions.rune.RuneRepository

class RuneRoomRepository: RuneRepository {
    private val runeDao: RuneDao = MainActivity.database.runeDao()
    private val allRunes: LiveData<List<com.example.domain.entities.Rune>>

    init {
        allRunes = runeDao.getAllRunes()
    }

    private class InsertAsyncTask internal constructor(private val dao: RuneDao):
        AsyncTask<com.example.domain.entities.Rune, Void, Void>() {
        override fun doInBackground(vararg params: com.example.domain.entities.Rune): Void? {
            dao.insert(params[0])
            return null
        }
    }
    private class DeleteAsyncTask internal constructor(private val dao: RuneDao):
        AsyncTask<com.example.domain.entities.Rune, Void, Void>() {
        override fun doInBackground(vararg params: com.example.domain.entities.Rune): Void?{
            dao.clearRunes(*params)
            return null
        }
    }
    private class InsertAllAsyncTask internal constructor(private val dao: RuneDao):
        AsyncTask<List<com.example.domain.entities.Rune>, Void, Void>() {
        override fun doInBackground(vararg params: List<com.example.domain.entities.Rune>): Void? {
            dao.insertAll(params[0])
            return null
        }
    }

    override fun saveRune(rune: com.example.domain.entities.Rune) {
        InsertAsyncTask(runeDao).execute(rune)
    }

    override fun saveRunes(runes: List<com.example.domain.entities.Rune>) {
        InsertAllAsyncTask(runeDao).execute(runes)
    }

    override fun getAllRunes() = allRunes

    override fun clearAllRunes() {
        val runeArray = allRunes.value?.toTypedArray()
        if(runeArray != null){
            DeleteAsyncTask(runeDao).execute(*runeArray)
        }
    }

    override fun getRuneById(id: Int) = runeDao.getRuneById(id)
    override suspend fun create(rune: com.example.domain.entities.Rune, onSuccess: () -> Unit) {
        TODO("Not yet implemented")
    }
}