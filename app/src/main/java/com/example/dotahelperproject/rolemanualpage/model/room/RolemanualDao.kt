package com.example.dotahelperproject.rolemanualpage.model.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.domain.entities.RoleManual

@Dao
interface RolemanualDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(rolemanual: com.example.domain.entities.RoleManual)

    @Delete
    fun clearRolemanuals(vararg rolemanual: com.example.domain.entities.RoleManual)

    @Query("SELECT * FROM roleManuals ORDER BY name ASC")
    fun getAllRolemanuals(): LiveData<List<com.example.domain.entities.RoleManual>>

    @Query("SELECT * FROM roleManuals WHERE roleManualId =:id")
    fun getRolemanualById(id: Int): LiveData<com.example.domain.entities.RoleManual>
}