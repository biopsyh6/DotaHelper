package com.example.domain.entities

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity (tableName = "hero_roles", primaryKeys = ["heroId", "roleId"],
    foreignKeys = [
        ForeignKey(entity = Hero::class, parentColumns = ["heroId"],
            childColumns = ["heroId"], onDelete = ForeignKey.CASCADE),
        ForeignKey(entity = Role::class, parentColumns = ["roleId"],
            childColumns = ["roleId"], onDelete = ForeignKey.CASCADE)
    ])
data class HeroRole (
    val heroId: Int = 0,
    val roleId: Int = 0,
    val firebaseId: String = ""
)