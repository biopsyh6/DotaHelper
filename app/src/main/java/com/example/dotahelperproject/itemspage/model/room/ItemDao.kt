package com.example.dotahelperproject.itemspage.model.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: com.example.domain.entities.Item)

    @Delete
    fun clearItems(vararg item: com.example.domain.entities.Item)

    @Query("SELECT * FROM items ORDER BY name ASC")
    fun getAllItems(): LiveData<List<com.example.domain.entities.Item>>

    @Query("SELECT * FROM items WHERE itemId =:id")
    fun getItemById(id: Int): LiveData<com.example.domain.entities.Item>

//    @Query("SELECT * FROM items WHERE categoryId =:categoryId")
//    fun getItemsByCategoryId(categoryId: Int): LiveData<List<com.example.domain.entities.Item>>
}