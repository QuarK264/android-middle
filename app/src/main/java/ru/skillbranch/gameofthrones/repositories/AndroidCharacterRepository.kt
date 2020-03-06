package ru.skillbranch.gameofthrones.repositories

import io.reactivex.Observable
import io.reactivex.Single
import kotlinx.collections.immutable.ImmutableList
import ru.skillbranch.gameofthrones.api.CharacterApi
import ru.skillbranch.gameofthrones.api.HouseApi
import ru.skillbranch.gameofthrones.api.model.mapToDomain
import ru.skillbranch.gameofthrones.core.domain.repositories.CharacterRepository
import ru.skillbranch.gameofthrones.core.extensions.mapImmutable
import ru.skillbranch.gameofthrones.data.local.entities.CharacterItem

class AndroidCharacterRepository(
    private val houseApi: HouseApi,
    private val characterApi: CharacterApi
) : CharacterRepository {

    override fun getAllForList(houseId: Long): Single<ImmutableList<CharacterItem>> {
        return houseApi
            .getHouse(houseId)
            .flatMap { house ->
                val characterIds = house
                    .swornMembers
                    .map {
                        it.split("/").last()
                    }

                Observable.fromIterable(characterIds)
                    .flatMapSingle { id ->
                        characterApi.getCharacter(id)
                    }
                    .toList()
                    .map { it.mapImmutable { character -> character.mapToDomain() } }
            }
    }
}