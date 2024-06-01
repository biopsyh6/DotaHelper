package com.example.domain.abstractions.item

import androidx.lifecycle.LiveData
import com.example.domain.entities.Item

interface ItemRepository {
    fun saveItem(item: Item)
    fun getAllItems(): LiveData<List<Item>>
    fun clearAllItems()
    fun getItemById(id: Int): LiveData<Item>
    fun getItemByCategoryId(categoryId: Int): LiveData<List<Item>>
    fun getItems() : LiveData<List<Item>>
    fun getItemsByTypeName(typeName: String) : LiveData<List<Item>>
    suspend fun create(item: Item, onSuccess: () -> Unit)
}