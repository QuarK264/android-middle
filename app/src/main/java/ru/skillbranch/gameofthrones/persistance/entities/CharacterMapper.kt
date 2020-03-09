package ru.skillbranch.gameofthrones.persistance.entities

import ru.skillbranch.gameofthrones.data.local.entities.CharacterItem

fun Character.mapToDomain() = CharacterItem(url, house, name, titles, aliases)