package com.example.domain.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.sql.Blob

@Entity(tableName = "skills", foreignKeys = [
    ForeignKey(entity = Hero::class, parentColumns = ["heroId"], childColumns = ["heroId"],
        onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE)
])
data class Skill(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "skillId")
    var skillId: Int? = null,
    var heroId: Int,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "description")
    var description: String,
    @ColumnInfo(name = "image")
    var image: Int,
    @ColumnInfo(name = "type")
    var type: String
)