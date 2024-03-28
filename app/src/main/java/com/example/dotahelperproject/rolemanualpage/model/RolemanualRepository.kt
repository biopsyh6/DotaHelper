package com.example.dotahelperproject.rolemanualpage.model

import androidx.lifecycle.LiveData
import com.example.dotahelperproject.entities.RoleManual

interface RolemanualRepository {
    fun saveRolemanual(rolemanual:RoleManual)
    fun getAllRolemanuals(): LiveData<List<RoleManual>>
    fun clearAllRolemanuals()
    fun getRolemanualById(id: Int): LiveData<RoleManual>
}