package ru.skillbranch.gameofthrones.core.domain.interaction

import io.reactivex.Single
import kotlinx.collections.immutable.ImmutableList
import ru.skillbranch.gameofthrones.api.GameOfThronesContracts
import ru.skillbranch.gameofthrones.core.base.Interactor
import ru.skillbranch.gameofthrones.core.domain.repositories.CharacterRepository
import ru.skillbranch.gameofthrones.data.local.entities.CharacterItem
import ru.skillbranch.gameofthrones.home.houses.pager.HousePageType

class GetCharactersInteractor(
    private val characterRepository: CharacterRepository
) : Interactor<HousePageType, Single<ImmutableList<CharacterItem>>> {

    override fun execute(request: HousePageType): Single<ImmutableList<CharacterItem>> {
        val houseId = when(request) {
            HousePageType.STARK -> GameOfThronesContracts.HouseId.STARK
            HousePageType.LANNISTER -> GameOfThronesContracts.HouseId.LANNISTER
            HousePageType.TARGARYEN -> GameOfThronesContracts.HouseId.TARGARYEN
            HousePageType.GREYJOY -> GameOfThronesContracts.HouseId.GREYJOY
            HousePageType.TYRELL -> GameOfThronesContracts.HouseId.TYRELL
            HousePageType.BARATHEON -> GameOfThronesContracts.HouseId.BARATHEON
            HousePageType.MARTELL -> GameOfThronesContracts.HouseId.MARTELL
        }

        return characterRepository.getAllForList(houseId)
    }
}
