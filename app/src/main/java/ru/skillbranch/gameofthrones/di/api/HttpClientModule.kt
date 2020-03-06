package ru.skillbranch.gameofthrones.di.api

import okhttp3.OkHttpClient
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton
import java.util.concurrent.TimeUnit

object HttpClientModule {
    val module = Kodein.Module("HttpClientModule") {
        bind() from singleton {
            createHttpClient()
        }
    }

    private fun createHttpClient(builder: OkHttpClient.Builder.() -> Unit = {}) =
        OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.MINUTES)
            .writeTimeout(10, TimeUnit.MINUTES)
            .readTimeout(10, TimeUnit.MINUTES)
            .callTimeout(10, TimeUnit.MINUTES)
            .apply {
                builder(this)
            }.build()
}