package ru.skillbranch.gameofthrones.core.domain.interaction

import io.reactivex.Single
import kotlinx.collections.immutable.ImmutableList
import ru.skillbranch.gameofthrones.core.base.Interactor
import ru.skillbranch.gameofthrones.core.domain.repositories.CharacterRepository
import ru.skillbranch.gameofthrones.data.local.entities.CharacterItem

class GetCharactersInteractor(
    private val characterRepository: CharacterRepository
) : Interactor<Unit, Single<ImmutableList<CharacterItem>>> {

    override fun execute(request: Unit): Single<ImmutableList<CharacterItem>> =
        characterRepository.getAllForList()
}
