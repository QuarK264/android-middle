package ru.skillbranch.gameofthrones.di

import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import ru.skillbranch.gameofthrones.core.domain.interaction.GetCharactersInteractor

object InteractionModule {
    val module = Kodein.Module("InteractionModule") {
        bind() from provider {
            GetCharactersInteractor(instance())
        }
    }
}