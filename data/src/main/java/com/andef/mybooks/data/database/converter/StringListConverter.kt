package com.andef.mybooks.data.database.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

//конвертер для преобразования List<String>
class StringListConverter {
    @TypeConverter
    fun fromStringList(value: List<String>?): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toStringList(value: String): List<String>? {
        return Gson().fromJson(value, object : TypeToken<List<String>?>() {}.type)
    }
}