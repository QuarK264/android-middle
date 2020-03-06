package ru.skillbranch.gameofthrones.di.api

import okhttp3.HttpUrl
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import retrofit2.Retrofit
import ru.skillbranch.gameofthrones.api.CharacterApi
import ru.skillbranch.gameofthrones.api.GameOfThronesContracts
import ru.skillbranch.gameofthrones.api.HouseApi

object ApiModule {
    val module = Kodein.Module("ApiModule") {
        importOnce(HttpClientModule.module)
        importOnce(RetrofitModule.module)

        bind() from singleton {
            instance<HttpUrl, Retrofit>(arg = instance<GameOfThronesContracts.Server>().api)
                .create(HouseApi::class.java)
        }

        bind() from singleton {
            instance<HttpUrl, Retrofit>(arg = instance<GameOfThronesContracts.Server>().api)
                .create(CharacterApi::class.java)
        }
    }
}