package ru.skillbranch.gameofthrones.core.extensions

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.immutableListOf

fun <T, Item> ImmutableList<T>.mapImmutable(mapper: (T) -> Item): ImmutableList<Item> {
    var result = immutableListOf<Item>()

    for (item in this) {
        result = result.add(mapper(item))
    }

    return result
}

fun <T, Item> List<T>.mapImmutable(mapper: (T) -> Item): ImmutableList<Item> {
    var result = immutableListOf<Item>()

    for (item in this) {
        result = result.add(mapper(item))
    }

    return result
}