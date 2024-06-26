package com.example.domain.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity (tableName = "heroes", foreignKeys = [
    ForeignKey(entity = Attribute::class, parentColumns = ["attributeId"],
        childColumns = ["attributeId"],
        onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE),
//    ForeignKey(entity = Role::class, parentColumns = ["roleId"], childColumns = ["roleId"],
//        onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE)
    ])
@Parcelize
data class Hero(
    @PrimaryKey(autoGenerate = true)
    var heroId: Int = 0,
    var attributeId: String = "",
//    var roleId: Int? = null,
    var roleIds: List<String> = listOf(),
    @ColumnInfo(name = "name")
    var name: String = "",
    @ColumnInfo(name = "health")
    var health: Int = 0,
    @ColumnInfo(name = "mana")
    var mana: Int = 0,
    @ColumnInfo(name = "strength")
    var strength: Int = 0,
    @ColumnInfo(name = "agility")
    var agility: Int = 0,
    @ColumnInfo(name = "intelligence")
    var intelligence: Int = 0,
    @ColumnInfo(name = "damage")
    var damage: String = "",
    @ColumnInfo(name = "armor")
    var armor: Double = 0.0,
    @ColumnInfo(name = "speed")
    var speed: Int = 0,
    @ColumnInfo(name = "attackRange")
    var attackRange: Int = 0,
    @ColumnInfo(name = "attackSpeed")
    var attackSpeed: Double = 0.0,
    @ColumnInfo(name = "image")
    var image: Int = 0,
    @ColumnInfo(name = "imageUrl")
    var imageUrl: String = "",
    @ColumnInfo(name = "description")
    var description: String = "",
    @ColumnInfo(name = "nameForUrl")
    var nameForUrl: String = "",
    var firebaseId: String = ""
) : Parcelable
