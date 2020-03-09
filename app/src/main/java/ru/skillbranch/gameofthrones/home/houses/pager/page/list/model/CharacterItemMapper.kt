package ru.skillbranch.gameofthrones.home.houses.pager.page.list.model

import ru.skillbranch.gameofthrones.R
import ru.skillbranch.gameofthrones.api.GameOfThronesContracts
import ru.skillbranch.gameofthrones.data.local.entities.CharacterItem as DomainCharacter
import ru.skillbranch.gameofthrones.home.houses.pager.page.list.CharactersAdapter

fun DomainCharacter.mapToListView() : CharacterItem {
    val logo = when (house.toLong()) {
        GameOfThronesContracts.HouseId.STARK -> R.drawable.stark_icon
        GameOfThronesContracts.HouseId.LANNISTER -> R.drawable.lannister_icon
        GameOfThronesContracts.HouseId.TARGARYEN -> R.drawable.targaryen_icon
        GameOfThronesContracts.HouseId.GREYJOY -> R.drawable.greyjoy_icon
        GameOfThronesContracts.HouseId.TYRELL -> R.drawable.tyrel_icon
        GameOfThronesContracts.HouseId.BARATHEON -> R.drawable.baratheon_icon
        GameOfThronesContracts.HouseId.MARTELL -> R.drawable.martel_icon
        else -> 0
    }

    return CharacterItem(
        CharactersAdapter.VIEW_TYPE_CHARACTER_ITEM,
        id,
        name,
        aliases.joinToString(),
        logo
    )
}