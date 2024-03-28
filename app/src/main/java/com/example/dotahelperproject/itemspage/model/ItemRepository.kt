package com.example.dotahelperproject.itemspage.model

import androidx.lifecycle.LiveData
import com.example.dotahelperproject.entities.Item

interface ItemRepository {
    fun saveItem(item: Item)
    fun getAllItems(): LiveData<List<Item>>
    fun clearAllItems()
    fun getItemById(id: Int): LiveData<Item>
    fun getItemByCategoryId(categoryId: Int): LiveData<List<Item>>
}