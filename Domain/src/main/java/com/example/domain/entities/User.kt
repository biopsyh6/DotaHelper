package com.example.domain.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User (
    @PrimaryKey(autoGenerate = true)
    var userId: Int? = null,
    @ColumnInfo(name = "login")
    var login: String = "",
    @ColumnInfo(name = "email")
    var email: String = "",
    @ColumnInfo(name = "steamid")
    val steamid: String = "",
    var firebaseId: String = ""
)