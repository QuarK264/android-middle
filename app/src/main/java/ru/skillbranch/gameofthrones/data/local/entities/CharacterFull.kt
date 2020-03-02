package ru.skillbranch.gameofthrones.data.local.entities

data class CharacterFull(
    val id: String,
    val name: String,
    val words: String,
    val born: String,
    val died: String,
    val titles: List<String>,
    val aliases: List<String>,
    val house: String, //rel
    val father: RelativeCharacter?,
    val mother: RelativeCharacter?
)