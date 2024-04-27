package com.example.domain.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.sql.Blob

@Entity(tableName = "items", foreignKeys = [
    ForeignKey(entity = ItemCategory::class, parentColumns = ["categoryId"],
        childColumns = ["categoryId"],
        onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE)
])
data class Item (
    @PrimaryKey(autoGenerate = true)
    var itemId: Int? = null,
    var categoryId: Int? = null,
    @ColumnInfo(name = "name")
    var name: String = "",
    @ColumnInfo(name = "price")
    var price: Int = 0,
    @ColumnInfo(name = "description")
    var description: String = "",
    @ColumnInfo(name = "activeAbility")
    var activeAbility: String = "",
    @ColumnInfo(name = "passiveAbility")
    var passiveAbility: String = "",
    @ColumnInfo(name = "bonuses")
    var bonuses: String = "",
    @ColumnInfo(name = "image")
    var image: Int = 0,
    var firebaseId: String = ""
)