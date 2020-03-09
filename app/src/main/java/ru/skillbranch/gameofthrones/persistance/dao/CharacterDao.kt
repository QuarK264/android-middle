package ru.skillbranch.gameofthrones.persistance.dao

import androidx.room.*
import io.reactivex.Single
import ru.skillbranch.gameofthrones.persistance.entities.Character

@Dao
interface CharacterDao {

    @Query("SELECT * FROM [character] WHERE house=:houseId")
    fun getAll(houseId: String): Single<List<Character>>

    @Query("SELECT * FROM [character] WHERE url=:id")
    fun getById(id: String): Single<Character>

    @Insert
    fun add(order: Character)

    @Update
    fun update(order: Character)

    @Delete
    fun delete(order: Character)
}
