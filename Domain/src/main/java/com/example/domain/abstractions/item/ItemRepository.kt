package com.example.domain.abstractions.item

import androidx.lifecycle.LiveData
import com.example.domain.entities.Item

interface ItemRepository {
    fun saveItem(item: com.example.domain.entities.Item)
    fun getAllItems(): LiveData<List<com.example.domain.entities.Item>>
    fun clearAllItems()
    fun getItemById(id: Int): LiveData<com.example.domain.entities.Item>
    fun getItemByCategoryId(categoryId: Int): LiveData<List<com.example.domain.entities.Item>>
}