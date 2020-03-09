package ru.skillbranch.gameofthrones.repositories

import android.annotation.SuppressLint
import io.reactivex.Observable
import io.reactivex.Single
import kotlinx.collections.immutable.ImmutableList
import ru.skillbranch.gameofthrones.api.CharacterApi
import ru.skillbranch.gameofthrones.api.HouseApi
import ru.skillbranch.gameofthrones.api.model.Character
import ru.skillbranch.gameofthrones.api.model.mapToDomain
import ru.skillbranch.gameofthrones.api.model.mapToRoom
import ru.skillbranch.gameofthrones.core.domain.repositories.CharacterRepository
import ru.skillbranch.gameofthrones.core.extensions.mapImmutable
import ru.skillbranch.gameofthrones.data.local.entities.CharacterItem
import ru.skillbranch.gameofthrones.persistance.GameOfThronesDatabase
import ru.skillbranch.gameofthrones.persistance.dao.CharacterDao
import ru.skillbranch.gameofthrones.persistance.entities.mapToDomain

class AndroidCharacterRepository(
    private val houseApi: HouseApi,
    private val characterApi: CharacterApi,
    private val gameOfThronesDatabase: GameOfThronesDatabase,
    private val characterDao: CharacterDao
) : CharacterRepository {

    override fun getAllForList(houseId: Long): Single<ImmutableList<CharacterItem>> {
        return characterDao
            .getAll(houseId.toString())
            .flatMap {
                if (it.any()) {
                    Single.fromCallable {
                        val mapImmutable = it.mapImmutable { character -> character.mapToDomain() }
                        mapImmutable
                    }
                } else {
                    val characters = houseApi
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
                        }
                    saveCharacters(characters)
                    val temp =
                        characters.map { it.mapImmutable { character -> character.mapToDomain() } }
                    temp
                }
            }
    }

    @SuppressLint("CheckResult")
    private fun saveCharacters(characters: Single<MutableList<Character>>) {
        characters
            .map { list ->
                list.map {
                    gameOfThronesDatabase.runInTransaction {
                        characterDao.add(it.mapToRoom())
                    }
                }
            }
    }
}