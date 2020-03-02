package ru.skillbranch.gameofthrones.home.houses.pager

enum class HousePageType {
    STARK,
    LANNISTER,
    TARGARYEN,
    GREYJOY,
    TYRELL,
    BARATHEON,
    MARTELL;

    companion object {
        fun forPosition(position: Int) : HousePageType =
            when(position) {
                STARK.ordinal -> STARK
                LANNISTER.ordinal -> LANNISTER
                TARGARYEN.ordinal -> TARGARYEN
                GREYJOY.ordinal -> GREYJOY
                TYRELL.ordinal -> TYRELL
                BARATHEON.ordinal -> BARATHEON
                MARTELL.ordinal -> MARTELL
                else -> throw IllegalStateException("Illegal position $position")
            }
    }
}