package ru.skillbranch.gameofthrones.core.domain.repositories

import io.reactivex.Single
import kotlinx.collections.immutable.ImmutableList
import ru.skillbranch.gameofthrones.data.local.entities.CharacterItem

interface CharacterRepository {
    fun getAllForList(): Single<ImmutableList<CharacterItem>>
}
