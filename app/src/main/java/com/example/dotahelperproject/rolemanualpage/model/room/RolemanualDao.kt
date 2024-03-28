package com.example.dotahelperproject.rolemanualpage.model.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dotahelperproject.entities.RoleManual

@Dao
interface RolemanualDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(rolemanual: RoleManual)

    @Delete
    fun clearRolemanuals(vararg rolemanual: RoleManual)

    @Query("SELECT * FROM roleManuals ORDER BY name ASC")
    fun getAllRolemanuals(): LiveData<List<RoleManual>>

    @Query("SELECT * FROM roleManuals WHERE roleManualId =:id")
    fun getRolemanualById(id: Int): LiveData<RoleManual>
}