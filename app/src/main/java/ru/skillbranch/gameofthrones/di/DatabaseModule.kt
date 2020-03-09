package ru.skillbranch.gameofthrones.di

import androidx.room.Room
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import ru.skillbranch.gameofthrones.persistance.GameOfThronesDatabase
import ru.skillbranch.gameofthrones.persistance.dao.CharacterDao

object DatabaseModule {
    val module = Kodein.Module("DatabaseModule") {
        bind() from singleton {
            Room.databaseBuilder(instance(), GameOfThronesDatabase::class.java, "GameOfThronesDatabase")
                .fallbackToDestructiveMigration()
                .build()
        }

        bind<CharacterDao>() with provider {
            instance<GameOfThronesDatabase>().characterDao()
        }
    }
}