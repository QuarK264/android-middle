package ru.skillbranch.gameofthrones.home.houses.pager.page.list

import io.reactivex.Observable
import kotlinx.collections.immutable.ImmutableList
import ru.skillbranch.gameofthrones.core.base.Item

interface HousePageScreen {
    val getCharacters: Observable<Unit>

    fun displayCharacters(characters: ImmutableList<Item>)
}