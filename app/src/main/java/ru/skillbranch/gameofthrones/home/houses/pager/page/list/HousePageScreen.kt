package ru.skillbranch.gameofthrones.home.houses.pager.page.list

import io.reactivex.Observable
import kotlinx.collections.immutable.ImmutableList
import ru.skillbranch.gameofthrones.core.base.Item
import ru.skillbranch.gameofthrones.home.houses.pager.HousePageType

interface HousePageScreen {
    val getCharacters: Observable<HousePageType>

    fun displayCharacters(characters: ImmutableList<Item>)
}