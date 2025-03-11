package com.example.dotahelperproject.converters

import androidx.room.TypeConverter
import com.example.domain.entities.Attributes
import com.example.domain.entities.SpecialValue
import com.google.common.reflect.TypeToken
import com.google.gson.Gson

class Converters {
    @TypeConverter
    fun fromString(value: String): List<String> {
        val type = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, type)
    }
    @TypeConverter
    fun fromList(list: List<String>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
    @TypeConverter
    fun fromSpecialValuesList(value: List<SpecialValue>): String {
        val gson = Gson()
        return gson.toJson(value)
    }
    @TypeConverter
    fun toSpecialValueList(value: String): List<SpecialValue> {
        val type = object : TypeToken<List<SpecialValue>>() {}.type
        return Gson().fromJson(value, type)
    }
    @TypeConverter
    fun fromAttributesList(value: List<Attributes>): String {
        val gson = Gson()
        return gson.toJson(value)
    }
    @TypeConverter
    fun toAttributesList(value: String): List<Attributes> {
        val type = object : TypeToken<List<Attributes>>() {}.type
        return Gson().fromJson(value, type)
    }
}