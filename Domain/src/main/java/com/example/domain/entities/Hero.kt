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
    var attributeId: Int,
    var roleId: Int,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "health")
    var health: Double,
    @ColumnInfo(name = "mana")
    var mana: Double,
    @ColumnInfo(name = "strength")
    var strength: Double,
    @ColumnInfo(name = "agility")
    var dexterity: Double,
    @ColumnInfo(name = "intelligence")
    var intelligence: Double,
    @ColumnInfo(name = "damage")
    var damage: Int,
    @ColumnInfo(name = "armor")
    var armor: Double,
    @ColumnInfo(name = "speed")
    var speed: Int,
    @ColumnInfo(name = "attackRange")
    var attackRange: Int,
    @ColumnInfo(name = "attackSpeed")
    var attackSpeed: Int
)