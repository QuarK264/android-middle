package ru.skillbranch.gameofthrones.persistance.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "character")
class Character(
    @PrimaryKey(autoGenerate = false) val url: String,
    val name: String,
    val culture: String,
    val born: String,
    val died: String,
    val titles: List<String>,
    val aliases: List<String>,
    val father: String,
    val mother: String,
    val spouse: String,
    val house: String,
    val books: List<String>,
    val povBooks: List<String>,
    val tvSeries: List<String>,
    val playedBy: List<String>
)