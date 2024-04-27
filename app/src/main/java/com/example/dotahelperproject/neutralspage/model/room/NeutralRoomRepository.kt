package com.example.dotahelperproject.neutralspage.model.room

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.dotahelperproject.MainActivity
import com.example.domain.abstractions.neutral.NeutralRepository

class NeutralRoomRepository: NeutralRepository {
    private val neutralDao: NeutralDao = MainActivity.database.neutralDao()
    private val allNeutrals: LiveData<List<com.example.domain.entities.Neutral>>

    init {
        allNeutrals = neutralDao.getAllNeutrals()
    }

    private class InsertAsyncTask internal constructor(private val dao: NeutralDao):
            AsyncTask<com.example.domain.entities.Neutral, Void, Void>() {
        override fun doInBackground(vararg params: com.example.domain.entities.Neutral): Void? {
            dao.insert(params[0])
            return null
        }
    }
    private class DeleteAsyncTask internal constructor(private val dao: NeutralDao):
            AsyncTask<com.example.domain.entities.Neutral, Void, Void>() {
        override fun doInBackground(vararg params: com.example.domain.entities.Neutral): Void?{
            dao.clearNeutrals(*params)
            return null
        }
    }

    override fun saveNeutral(neutral: com.example.domain.entities.Neutral) {
        InsertAsyncTask(neutralDao).execute(neutral)
    }

    override fun getAllNeutrals() = allNeutrals

    override fun clearAllNeutrals() {
        val neutralArray = allNeutrals.value?.toTypedArray()
        if(neutralArray != null){
            DeleteAsyncTask(neutralDao).execute(*neutralArray)
        }
    }

    override fun getNeutralById(id: Int) = neutralDao.getNeutralById(id)
}