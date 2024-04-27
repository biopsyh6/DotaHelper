package com.example.domain.abstractions.attribute

import androidx.lifecycle.LiveData
import com.example.domain.entities.Attribute

interface AttributeRepository {
    fun saveAttribute(attribute: Attribute)
    fun saveAttributes(attributes: List<Attribute>)
    fun getAllAttributes(): LiveData<List<Attribute>>
    fun clearAllAttributes()
    fun getAttributeById(id: Int): LiveData<Attribute>
    suspend fun create(attribute: Attribute, onSuccess: () -> Unit)
}