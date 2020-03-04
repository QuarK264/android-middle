package ru.skillbranch.gameofthrones.repositories

import io.reactivex.Single
import kotlinx.collections.immutable.ImmutableList
import ru.skillbranch.gameofthrones.core.domain.repositories.CharacterRepository
import ru.skillbranch.gameofthrones.data.local.entities.CharacterItem

class AndroidCharacterRepository : CharacterRepository {
    override fun getAllForList(): Single<ImmutableList<CharacterItem>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}