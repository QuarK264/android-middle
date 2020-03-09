package ru.skillbranch.gameofthrones.api

import okhttp3.HttpUrl

object GameOfThronesContracts {

    enum class Server {
        DEV;

        val api: HttpUrl
            get() = when (this) {
                DEV -> HttpUrl.Builder()
                    .scheme(SCHEME_HTTPS)
                    .host(HOST_URL)
                    //.port(PORT_DEV)
                    .addPathSegments(API_PREFIX)
                    .build()
            }

        companion object {
            const val SCHEME_HTTPS = "https"

            const val HOST_URL = "www.anapioficeandfire.com"

            const val PORT_DEV = 0

            const val API_PREFIX = "api/"
        }
    }

    class HouseId {
        companion object {
            const val STARK = 362L
            const val LANNISTER = 229L
            const val TARGARYEN = 378L
            const val GREYJOY = 169L
            const val TYRELL = 398L
            const val BARATHEON = 17L
            const val MARTELL = 285L
        }
    }
}
