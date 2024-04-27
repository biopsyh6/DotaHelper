package com.example.domain.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Blob

@Entity(tableName = "attributes")
data class Attribute(
    @PrimaryKey(autoGenerate = true)
    var attributeId: Int? = null,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "image")
    var image: Int
)