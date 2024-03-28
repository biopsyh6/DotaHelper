package com.example.dotahelperproject.itemspage.model.room

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.dotahelperproject.MainActivity
import com.example.dotahelperproject.entities.Item
import com.example.dotahelperproject.itemspage.model.ItemRepository

class ItemRoomRepository : ItemRepository {
    private val itemDao: ItemDao = MainActivity.database.itemDao()
    private val allItems: LiveData<List<Item>>

    init {
        allItems = itemDao.getAllItems()
    }

    override fun saveItem(item: Item) {
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

    private class InsertAsyncTask internal constructor(private val dao: ItemDao):
        AsyncTask<Item, Void, Void>() {
        override fun doInBackground(vararg params: Item): Void? {
            dao.insert(params[0])
            return null
        }
    }
    private class DeleteAsyncTask internal constructor(private val dao: ItemDao):
            AsyncTask<Item, Void, Void>() {
        override fun doInBackground(vararg params: Item): Void?{
            dao.clearItems(*params)
            return null
        }
    }
}