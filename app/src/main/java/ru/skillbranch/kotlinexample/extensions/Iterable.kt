package ru.skillbranch.kotlinexample.extensions

fun<T> List<T>.dropLastUntil(predicate: (T) -> Boolean): List<T> {
    val list = mutableListOf<T>()
    for (item in this) {
        if (predicate(item)) {
            break
        }
        list.add(item)
    }
    return list
}