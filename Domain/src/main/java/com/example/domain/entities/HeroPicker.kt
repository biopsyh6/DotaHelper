package com.example.domain.entities

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.parcelize.Parcelize

@Entity (tableName = "heroespick")

@Parcelize
data class HeroPickerResponse(
    var status: Int = 0,
    var data: List<HeroPicker> = listOf()
) : Parcelable

@Parcelize
data class HeroPicker(
    var id: Int = 0,
    var name: String = "",
    var primary_attr: String = "",
    var attack_type: String = "",
    var img: String = "",
    var roles: List<String> = listOf(),
    var bad_against: List<String> = listOf(),
    var good_against: List<String> = listOf(),
    var firebaseId: String = ""
) : Parcelable
