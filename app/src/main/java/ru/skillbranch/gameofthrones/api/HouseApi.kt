package ru.skillbranch.gameofthrones.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import ru.skillbranch.gameofthrones.api.model.House

interface HouseApi {

    @GET("houses/{id}")
    fun getHouse(@Path("id") id: Long): Single<House>
}