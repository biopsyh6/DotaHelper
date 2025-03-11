package com.example.dotahelperproject.role.model.room

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.domain.abstractions.role.RoleRepository
import com.example.domain.entities.Role
import com.example.dotahelperproject.MainActivity

class RoleRoomRepository: RoleRepository {
    private val roleDao: RoleDao = MainActivity.database.roleDao()
    private val allRoles: LiveData<List<com.example.domain.entities.Role>>
    init {
        allRoles = roleDao.getAllRoles()
    }

    private class InsertAsyncTask internal constructor(private val dao: RoleDao):
            AsyncTask<com.example.domain.entities.Role, Void, Void>() {
        override fun doInBackground(vararg params: com.example.domain.entities.Role): Void? {
            dao.insert(params[0])
            return null
        }
    }
    private class DeleteAsyncTask internal constructor(private val dao: RoleDao):
            AsyncTask<com.example.domain.entities.Role, Void, Void>() {
        override fun doInBackground(vararg params: com.example.domain.entities.Role): Void? {
            dao.clearRoles(*params)
            return null
        }
    }

    private class InsertAllAsyncTask internal constructor(private val dao: RoleDao):
        AsyncTask<List<com.example.domain.entities.Role>, Void, Void>() {
        override fun doInBackground(vararg params: List<com.example.domain.entities.Role>): Void? {
            dao.insertAll(params[0])
            return null
        }
    }

    override fun saveRole(role: com.example.domain.entities.Role) {
        InsertAsyncTask(roleDao).execute(role)
    }

    override fun saveRoles(roles: List<com.example.domain.entities.Role>) {
        InsertAllAsyncTask(roleDao).execute(roles)
    }

    override fun getAllRoles() = allRoles

    override fun clearAllRoles() {
        val roleArray = allRoles.value?.toTypedArray()
        if(roleArray != null){
            DeleteAsyncTask(roleDao).execute(*roleArray)
        }
    }

    override fun getRoleById(id: Int) = roleDao.getRoleById(id)
    override fun getRolesByRoleIds(roleIds: List<String>): LiveData<List<Role>> {
        TODO("Not yet implemented")
    }


    override suspend fun create(role: Role, onSuccess: () -> Unit) {
        TODO("Not yet implemented")
    }
}