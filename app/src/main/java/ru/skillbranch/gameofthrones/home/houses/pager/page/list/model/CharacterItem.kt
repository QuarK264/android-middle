package ru.skillbranch.gameofthrones.home.houses.pager.page.list.model

import ru.skillbranch.gameofthrones.core.base.Item

class CharacterItem(
    viewType: Int,
    val id: String,
    val name: String,
    val aliases: String,
    val houseLogo: Int
) : Item(viewType)
