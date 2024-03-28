package com.example.dotahelperproject.rolemanualpage.model.room

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.dotahelperproject.MainActivity
import com.example.dotahelperproject.entities.RoleManual
import com.example.dotahelperproject.rolemanualpage.model.RolemanualRepository

class RolemanualRoomRepository: RolemanualRepository {
    private val rolemanualDao: RolemanualDao = MainActivity.database.rolemanualDao()
    private val allRolemanuals: LiveData<List<RoleManual>>
    init {
        allRolemanuals = rolemanualDao.getAllRolemanuals()
    }
    private class InsertAsyncTask internal constructor(private val dao: RolemanualDao):
            AsyncTask<RoleManual, Void, Void>() {
        override fun doInBackground(vararg params: RoleManual): Void? {
            dao.insert(params[0])
            return null
        }
    }
    private class DeleteAsyncTask internal constructor(private val dao: RolemanualDao):
            AsyncTask<RoleManual, Void, Void>() {
        override fun doInBackground(vararg params: RoleManual): Void? {
            dao.clearRolemanuals(*params)
            return null
        }
    }

    override fun saveRolemanual(rolemanual: RoleManual) {
        InsertAsyncTask(rolemanualDao).execute(rolemanual)
    }

    override fun getAllRolemanuals() = allRolemanuals

    override fun clearAllRolemanuals() {
        val rolemanualArray = allRolemanuals.value?.toTypedArray()
        if(rolemanualArray != null){
            DeleteAsyncTask(rolemanualDao).execute(*rolemanualArray)
        }
    }

    override fun getRolemanualById(id: Int) = rolemanualDao.getRolemanualById(id)

}