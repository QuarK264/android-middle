package ru.skillbranch.gameofthrones.api.model

import ru.skillbranch.gameofthrones.data.local.entities.CharacterItem
import ru.skillbranch.gameofthrones.persistance.entities.Character as RoomCharacter

fun Character.mapToDomain() =
    CharacterItem(url, allegiances.first().split("/").last(), name, titles, aliases)

fun Character.mapToRoom() = RoomCharacter(
    url,
    name,
    culture,
    born,
    died,
    titles,
    aliases,
    father,
    mother,
    spouse,
    allegiances.first().split("/").last(),
    books,
    povBooks,
    tvSeries,
    playedBy
)