package com.example.domain.abstractions.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.entities.User

interface UserRepository {
    fun saveUser(user: User)
    fun saveUsers(users: List<User>)
    fun getAllUsers(): LiveData<List<User>>
    fun clearAllUsers()
    fun getUserById(id: Int): MutableLiveData<User?>
    suspend fun create(user: User, onSuccess: () -> Unit)
    fun getSteamId(): MutableLiveData<String?>
    fun getEmail(): MutableLiveData<String?>
}