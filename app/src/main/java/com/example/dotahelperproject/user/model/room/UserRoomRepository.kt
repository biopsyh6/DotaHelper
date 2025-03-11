package com.example.dotahelperproject.user.model.room

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.abstractions.user.UserRepository
import com.example.domain.entities.User
import com.example.dotahelperproject.MainActivity

class UserRoomRepository: UserRepository {
    private val userDao: UserDao = MainActivity.database.userDao()
    private val allUsers: LiveData<List<com.example.domain.entities.User>>

    init {
        allUsers = userDao.getAllUsers()
    }

    private class InsertAsyncTask internal constructor(private val dao: UserDao):
        AsyncTask<com.example.domain.entities.User, Void, Void>() {
        override fun doInBackground(vararg params: com.example.domain.entities.User): Void? {
            dao.insert(params[0])
            return null
        }
    }
    private class DeleteAsyncTask internal constructor(private val dao: UserDao):
        AsyncTask<com.example.domain.entities.User, Void, Void>() {
        override fun doInBackground(vararg params: com.example.domain.entities.User): Void?{
            dao.clearUsers(*params)
            return null
        }
    }
    private class InsertAllAsyncTask internal constructor(private val dao: UserDao):
        AsyncTask<List<com.example.domain.entities.User>, Void, Void>() {
        override fun doInBackground(vararg params: List<com.example.domain.entities.User>): Void? {
            dao.insertAll(params[0])
            return null
        }
    }

    override fun saveUser(user: com.example.domain.entities.User) {
        InsertAsyncTask(userDao).execute(user)
    }

    override fun saveUsers(users: List<com.example.domain.entities.User>) {
        InsertAllAsyncTask(userDao).execute(users)
    }

    override fun getAllUsers() = allUsers

    override fun clearAllUsers() {
        val userArray = allUsers.value?.toTypedArray()
        if(userArray != null){
            DeleteAsyncTask(userDao).execute(*userArray)
        }
    }

    override fun getUserById(id: Int): MutableLiveData<User?> {
        TODO("Not yet implemented")
    }


    override suspend fun create(user: com.example.domain.entities.User, onSuccess: () -> Unit) {
        TODO("Not yet implemented")
    }

    override fun getSteamId(): MutableLiveData<String?> {
        TODO("Not yet implemented")
    }

    override fun getEmail(): MutableLiveData<String?> {
        TODO("Not yet implemented")
    }
}