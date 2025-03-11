package com.example.dotahelperproject.role.model.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.domain.entities.Role

@Dao
interface RoleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(role: Role)

    @Delete
    fun clearRoles(vararg role: Role)

    @Query("SELECT * FROM roles ORDER BY name ASC")
    fun getAllRoles(): LiveData<List<Role>>

    @Query("SELECT * FROM roles WHERE roleId =:id")
    fun getRoleById(id: Int): LiveData<Role>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(roles: List<Role>)
}