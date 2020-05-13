package ru.skillbranch.skillarticles.extensions

fun String?.indexesOf(subStr: String, ignoreCase: Boolean = true): List<Int> {
    if (this.isNullOrEmpty()) return emptyList()
    if (subStr.isEmpty()) return emptyList()
    val subString = if (ignoreCase) subStr.toLowerCase() else subStr
    val indexes = mutableListOf<Int>()
    var index = this.indexOf(subString, ignoreCase =  ignoreCase)
    while (index >= 0) {
        indexes.add(index)
        index = this.indexOf(subString, index + 1, ignoreCase)
    }
    return indexes
}