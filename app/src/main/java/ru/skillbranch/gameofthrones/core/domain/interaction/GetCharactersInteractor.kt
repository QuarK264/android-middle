package ru.skillbranch.gameofthrones.core.domain.interaction

import io.reactivex.Single
import kotlinx.collections.immutable.ImmutableList
import ru.skillbranch.gameofthrones.core.base.Interactor
import ru.skillbranch.gameofthrones.core.domain.repositories.CharacterRepository
import ru.skillbranch.gameofthrones.data.local.entities.CharacterItem
import ru.skillbranch.gameofthrones.home.houses.pager.HousePageType

class GetCharactersInteractor(
    private val characterRepository: CharacterRepository
) : Interactor<HousePageType, Single<ImmutableList<CharacterItem>>> {

    override fun execute(request: HousePageType): Single<ImmutableList<CharacterItem>> {
        val houseId = when(request) {
            HousePageType.STARK -> 362L
            HousePageType.LANNISTER -> 229L
            HousePageType.TARGARYEN -> 378L
            HousePageType.GREYJOY -> 169L
            HousePageType.TYRELL -> 398L
            HousePageType.BARATHEON -> 17L
            HousePageType.MARTELL -> 285L
        }

        return characterRepository.getAllForList(houseId)
    }
}
