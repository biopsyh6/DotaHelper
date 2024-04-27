package com.example.domain.abstractions.attribute

import androidx.lifecycle.LiveData
import com.example.domain.entities.Attribute

interface AttributeRepository {
    fun saveAttribute(attribute: com.example.domain.entities.Attribute)
    fun saveAttributes(attributes: List<com.example.domain.entities.Attribute>)
    fun getAllAttributes(): LiveData<List<com.example.domain.entities.Attribute>>
    fun clearAllAttributes()
    fun getAttributeById(id: Int): LiveData<com.example.domain.entities.Attribute>
}