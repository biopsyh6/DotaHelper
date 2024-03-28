package com.example.dotahelperproject.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "itemCategories")
data class ItemCategory (
    @PrimaryKey(autoGenerate = true)
    var categoryId: Int? = null,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "description")
    var description: String
)