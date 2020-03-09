package ru.skillbranch.gameofthrones.persistance

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.skillbranch.gameofthrones.persistance.converters.ListStringConverter
import ru.skillbranch.gameofthrones.persistance.dao.CharacterDao
import ru.skillbranch.gameofthrones.persistance.entities.Character

@Database(entities = [Character::class], version = 2)
@TypeConverters(ListStringConverter::class)
abstract class GameOfThronesDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}