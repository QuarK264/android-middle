package ru.skillbranch.gameofthrones.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import ru.skillbranch.gameofthrones.api.model.Character

interface CharacterApi {

    @GET("characters/{id}")
    fun getCharacter(@Path("id") id: String) : Single<Character>
}
