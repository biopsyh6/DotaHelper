package com.example.dotahelperproject.attribute.model.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.domain.entities.Attribute

@Dao
interface AttributeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(attribute: com.example.domain.entities.Attribute)

    @Delete
    fun clearAttributes(vararg attribute: com.example.domain.entities.Attribute)

    @Query("SELECT * FROM attributes ORDER BY name ASC")
    fun getAllAttributes(): LiveData<List<com.example.domain.entities.Attribute>>

    @Query("SELECT * FROM attributes WHERE attributeId =:id")
    fun getAttributeById(id: Int): LiveData<com.example.domain.entities.Attribute>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(attributes: List<com.example.domain.entities.Attribute>)
}