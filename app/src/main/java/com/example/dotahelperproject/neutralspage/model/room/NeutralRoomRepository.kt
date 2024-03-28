package com.example.dotahelperproject.neutralspage.model.room

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.dotahelperproject.MainActivity
import com.example.dotahelperproject.entities.Neutral
import com.example.dotahelperproject.neutralspage.model.NeutralRepository

class NeutralRoomRepository: NeutralRepository {
    private val neutralDao: NeutralDao = MainActivity.database.neutralDao()
    private val allNeutrals: LiveData<List<Neutral>>

    init {
        allNeutrals = neutralDao.getAllNeutrals()
    }

    private class InsertAsyncTask internal constructor(private val dao: NeutralDao):
            AsyncTask<Neutral, Void, Void>() {
        override fun doInBackground(vararg params: Neutral): Void? {
            dao.insert(params[0])
            return null
        }
    }
    private class DeleteAsyncTask internal constructor(private val dao: NeutralDao):
            AsyncTask<Neutral, Void, Void>() {
        override fun doInBackground(vararg params: Neutral): Void?{
            dao.clearNeutrals(*params)
            return null
        }
    }

    override fun saveNeutral(neutral: Neutral) {
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