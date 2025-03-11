package com.example.dotahelperproject.attribute.model.room

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.abstractions.attribute.AttributeRepository
import com.example.domain.entities.Attribute
import com.example.dotahelperproject.MainActivity

class AttributeRoomRepository: AttributeRepository {
    private val attributeDao: AttributeDao = MainActivity.database.attributeDao()
    private val allAttributes: LiveData<List<com.example.domain.entities.Attribute>>
    init {
        allAttributes = attributeDao.getAllAttributes()
    }
    private class InsertAsyncTask internal constructor(private val dao: AttributeDao):
            AsyncTask<com.example.domain.entities.Attribute, Void, Void>() {
        override fun doInBackground(vararg params: com.example.domain.entities.Attribute): Void? {
            dao.insert(params[0])
            return null
        }
    }
    private class DeleteAsyncTask internal constructor(private val dao: AttributeDao):
        AsyncTask<com.example.domain.entities.Attribute, Void, Void>() {
        override fun doInBackground(vararg params: com.example.domain.entities.Attribute): Void? {
            dao.clearAttributes(*params)
            return null
        }
    }
    private class InsertAllAsyncTask internal constructor(private val dao: AttributeDao):
        AsyncTask<List<com.example.domain.entities.Attribute>, Void, Void>() {
        override fun doInBackground(vararg params: List<com.example.domain.entities.Attribute>): Void? {
            dao.insertAll(params[0])
            return null
        }
    }

    override fun saveAttribute(attribute: com.example.domain.entities.Attribute) {
        InsertAsyncTask(attributeDao).execute(attribute)
    }

    override fun saveAttributes(attributes: List<com.example.domain.entities.Attribute>) {
        InsertAllAsyncTask(attributeDao).execute(attributes)
    }

//    override fun getAllAttributes() = allAttributes

    override fun clearAllAttributes() {
        val attributeArray = allAttributes.value?.toTypedArray()
        if(attributeArray != null){
            DeleteAsyncTask(attributeDao).execute(*attributeArray)
        }
    }

    override fun getAttributeById(id: Int): MutableLiveData<Attribute?> {
        TODO("Not yet implemented")
    }

//    override fun getAttributeById(id: Int) = attributeDao.getAttributeById(id)

    override suspend fun create(attribute: Attribute, onSuccess: () -> Unit) {
        TODO("Not yet implemented")
    }

    override fun getAttributeFirebaseIdByName(name: String): LiveData<String?> {
        TODO("Not yet implemented")
    }


    override fun getAllAttributes(): MutableLiveData<List<Attribute>> {
        TODO("Not yet implemented")
    }
}