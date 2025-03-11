package com.example.dotahelperproject.user.model.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.domain.entities.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: com.example.domain.entities.User)

    @Delete
    fun clearUsers(vararg user: com.example.domain.entities.User)

    @Query("SELECT * FROM users ORDER BY login ASC")
    fun getAllUsers(): LiveData<List<com.example.domain.entities.User>>

    @Query("SELECT * FROM users WHERE userId =:id")
    fun getUserById(id: Int): LiveData<com.example.domain.entities.User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: List<com.example.domain.entities.User>)
}