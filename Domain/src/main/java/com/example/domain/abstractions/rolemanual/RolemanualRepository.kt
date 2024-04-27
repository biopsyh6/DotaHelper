package com.example.domain.abstractions.rolemanual

import androidx.lifecycle.LiveData
import com.example.domain.entities.RoleManual

interface RolemanualRepository {
    fun saveRolemanual(rolemanual: com.example.domain.entities.RoleManual)
    fun getAllRolemanuals(): LiveData<List<com.example.domain.entities.RoleManual>>
    fun clearAllRolemanuals()
    fun getRolemanualById(id: Int): LiveData<com.example.domain.entities.RoleManual>
}