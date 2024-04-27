package com.example.domain.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Blob

@Entity(tableName = "neutrals")
data class Neutral (
    @PrimaryKey(autoGenerate = true)
    var neutralId: Int? = null,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "description")
    var description: String,
    @ColumnInfo(name = "generalGold")
    var generalGold: Int,
    @ColumnInfo(name = "generalExp")
    var generalExp: Int,
    @ColumnInfo(name = "generalHp")
    var generalHp: Int,
    @ColumnInfo(name = "skills")
    var skills: String,
    @ColumnInfo(name = "image")
    var image: Int
)
