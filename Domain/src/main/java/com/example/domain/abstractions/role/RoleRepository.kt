package com.example.domain.abstractions.role

import androidx.lifecycle.LiveData
import com.example.domain.entities.Role

interface RoleRepository {

    fun saveRole(role: Role)

    fun saveRoles(roles: List<Role>)

    fun getAllRoles(): LiveData<List<Role>>

    fun clearAllRoles()

    fun getRoleById(id: Int): LiveData<Role>
    fun getRolesByRoleIds(roleIds: List<String>): LiveData<List<Role>>
    suspend fun create(role: Role, onSuccess: () -> Unit)
}