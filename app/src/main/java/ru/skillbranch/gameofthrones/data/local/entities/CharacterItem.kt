package ru.skillbranch.gameofthrones.data.local.entities

data class CharacterItem(
    val id: String,
    val house: String, //rel
    val name: String,
    val titles: List<String>,
    val aliases: List<String>
)