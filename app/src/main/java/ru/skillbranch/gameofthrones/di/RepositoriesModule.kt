package ru.skillbranch.gameofthrones.di

import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import ru.skillbranch.gameofthrones.core.domain.repositories.CharacterRepository
import ru.skillbranch.gameofthrones.repositories.AndroidCharacterRepository

object RepositoriesModule {
    val module = Kodein.Module("RepositoriesModule") {
        bind<CharacterRepository>() with singleton {
            AndroidCharacterRepository(instance(), instance(), instance(), instance())
        }
    }
}