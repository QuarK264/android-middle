package ru.skillbranch.gameofthrones.api.model

import ru.skillbranch.gameofthrones.data.local.entities.CharacterItem

fun Character.mapToDomain() = CharacterItem(url, allegiances.first(), name, titles, aliases)