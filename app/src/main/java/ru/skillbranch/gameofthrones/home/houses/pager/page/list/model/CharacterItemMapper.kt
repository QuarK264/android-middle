package ru.skillbranch.gameofthrones.home.houses.pager.page.list.model

import ru.skillbranch.gameofthrones.data.local.entities.CharacterItem as DomainCharacter
import ru.skillbranch.gameofthrones.home.houses.pager.page.list.CharactersAdapter

fun DomainCharacter.mapToListView() = CharacterItem(CharactersAdapter.VIEW_TYPE_CHARACTER_ITEM, id, name, aliases.joinToString(), 0)