package ru.skillbranch.skillarticles.extensions

fun String?.indexesOf(substr: String, ignoreCase: Boolean = true): List<Int> {
    if (this.isNullOrEmpty()) return emptyList()
    val subString = if (ignoreCase) substr.toLowerCase() else substr
    var index = 0
    val indexes = mutableListOf<Int>()
    while (index >= 0) {
        index = this.indexOf(subString, index, ignoreCase)
        indexes.add(index)
    }
    return indexes
}