package com.example.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SpecialValue (
    var name: String = "",
    var values: List<Float> = listOf(),
    var isPercentage: Boolean = false,
    var heading: String = ""
) : Parcelable