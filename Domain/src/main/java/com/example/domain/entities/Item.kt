package com.example.domain.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//@Entity(tableName = "items", foreignKeys = [
//    ForeignKey(entity = ItemCategory::class, parentColumns = ["categoryId"],
//        childColumns = ["categoryId"],
//        onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE)
//])
@Entity(tableName = "items")
data class Item (
    @PrimaryKey(autoGenerate = true)
    var itemId: Int = 0,
//    var categoryId: Int? = null,
    @ColumnInfo(name = "name")
    var name: String = "",
    @ColumnInfo(name = "price")
    var price: String = "",
    @ColumnInfo(name = "description")
    var description: String = "",
//    @ColumnInfo(name = "activeAbility")
//    var activeAbility: String = "",
//    @ColumnInfo(name = "passiveAbility")
//    var passiveAbility: String = "",
//    @ColumnInfo(name = "bonuses")
//    var bonuses: String = "",
    @ColumnInfo(name = "image")
    var image: Int = 0,
    @ColumnInfo(name = "imageUrl")
    var imageUrl: String = "",
    var firebaseId: String = ""
)