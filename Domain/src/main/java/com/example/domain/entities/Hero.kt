package com.example.domain.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity (tableName = "heroes", foreignKeys = [
    ForeignKey(entity = Attribute::class, parentColumns = ["attributeId"],
        childColumns = ["attributeId"],
        onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE),
    ForeignKey(entity = Role::class, parentColumns = ["roleId"], childColumns = ["roleId"],
        onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE)
    ])
data class Hero (
    @PrimaryKey(autoGenerate = true)
    var heroId: Int? = null,
    var attributeId: Int? = null,
    var roleId: Int? = null,
    @ColumnInfo(name = "name")
    var name: String = "",
    @ColumnInfo(name = "health")
    var health: Double = 0.0,
    @ColumnInfo(name = "mana")
    var mana: Double = 0.0,
    @ColumnInfo(name = "strength")
    var strength: Double = 0.0,
    @ColumnInfo(name = "agility")
    var agility: Double = 0.0,
    @ColumnInfo(name = "intelligence")
    var intelligence: Double = 0.0,
    @ColumnInfo(name = "damage")
    var damage: Int = 0,
    @ColumnInfo(name = "armor")
    var armor: Double = 0.0,
    @ColumnInfo(name = "speed")
    var speed: Int = 0,
    @ColumnInfo(name = "attackRange")
    var attackRange: Int = 0,
    @ColumnInfo(name = "attackSpeed")
    var attackSpeed: Int = 0,
    @ColumnInfo(name = "image")
    var image: Int = 0,
    var firebaseId: String = ""
)