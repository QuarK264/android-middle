package ru.skillbranch.gameofthrones.persistance.converters

import androidx.room.TypeConverter

class ListStringConverter {

    @TypeConverter
    fun fromListString(strings: List<String>) = strings.joinToString(",")

    @TypeConverter
    fun toListString(string: String) = string.split(",")
}