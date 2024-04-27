package com.example.domain.abstractions.item

import androidx.lifecycle.LiveData
import com.example.domain.entities.Item

interface ItemRepository {
    fun saveItem(item: Item)
    fun getAllItems(): LiveData<List<Item>>
    fun clearAllItems()
    fun getItemById(id: Int): LiveData<Item>
    fun getItemByCategoryId(categoryId: Int): LiveData<List<Item>>
    suspend fun create(item: Item, onSuccess: () -> Unit)
}