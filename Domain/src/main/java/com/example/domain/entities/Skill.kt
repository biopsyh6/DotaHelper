package com.example.domain.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "skills", foreignKeys = [
    ForeignKey(entity = Hero::class, parentColumns = ["heroId"], childColumns = ["heroId"],
        onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE)
])
@Parcelize
data class Skill(
    @PrimaryKey(autoGenerate = true) //@ColumnInfo(name = "skillId")
    var skillId: Int = 0,
    var heroId: String = "",
    @ColumnInfo(name = "skillName")
    var skillName: String = "",
    @ColumnInfo(name = "heroName")
    var heroName: String = "",
    @ColumnInfo(name = "description")
    var description: String = "",
    @ColumnInfo(name = "image")
    var image: Int = 0,
    @ColumnInfo(name = "imageUrl")
    var imageUrl: String = "",
    @ColumnInfo(name = "damageType")
    var damageType: String = "",
    @ColumnInfo(name = "maxLevel")
    var maxLevel: Int = 0,
    @ColumnInfo(name = "specialValues")
    var specialValues: List<SpecialValue> = listOf(),
    @ColumnInfo(name = "abilityHasScepter")
    var abilityHasScepter: Boolean = false,
    @ColumnInfo(name = "abilityHasShard")
    var abilityHasShard: Boolean = false,
    @ColumnInfo(name = "abilityIsGrantedByScepter")
    var abilityIsGrantedByScepter: Boolean = false,
    @ColumnInfo(name = "abilityIsGrantedByShard")
    var abilityIsGrantedByShard: Boolean = false,
    @ColumnInfo(name = "shardDescription")
    var shardDescription: String = "",
    @ColumnInfo(name = "scepterDescription")
    var scepterDescription: String = "",
    var firebaseId: String = ""
) : Parcelable