package com.example.dotahelperproject.itemspage.model.room

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.dotahelperproject.MainActivity
import com.example.domain.abstractions.item.ItemRepository
import com.example.domain.entities.Item

class ItemRoomRepository : ItemRepository {
    private val itemDao: ItemDao = MainActivity.database.itemDao()
    private val allItems: LiveData<List<com.example.domain.entities.Item>>

    init {
        allItems = itemDao.getAllItems()
    }

    override fun saveItem(item: com.example.domain.entities.Item) {
        InsertAsyncTask(itemDao).execute(item)
    }

    override fun getAllItems() = allItems

    override fun clearAllItems() {
        val itemArray = allItems.value?.toTypedArray()
        if(itemArray != null){
            DeleteAsyncTask(itemDao).execute(*itemArray)
        }
    }

    override fun getItemById(id: Int) = itemDao.getItemById(id)

    override fun getItemByCategoryId(categoryId: Int) = itemDao.getItemsByCategoryId(categoryId)
    override suspend fun create(item: Item, onSuccess: () -> Unit) {
        TODO("Not yet implemented")
    }

    private class InsertAsyncTask internal constructor(private val dao: ItemDao):
        AsyncTask<com.example.domain.entities.Item, Void, Void>() {
        override fun doInBackground(vararg params: com.example.domain.entities.Item): Void? {
            dao.insert(params[0])
            return null
        }
    }
    private class DeleteAsyncTask internal constructor(private val dao: ItemDao):
            AsyncTask<com.example.domain.entities.Item, Void, Void>() {
        override fun doInBackground(vararg params: com.example.domain.entities.Item): Void?{
            dao.clearItems(*params)
            return null
        }
    }
}