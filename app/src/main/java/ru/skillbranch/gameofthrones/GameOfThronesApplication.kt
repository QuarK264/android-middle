package ru.skillbranch.gameofthrones

import android.app.Application
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import ru.skillbranch.gameofthrones.api.GameOfThronesContracts
import ru.skillbranch.gameofthrones.di.DatabaseModule
import ru.skillbranch.gameofthrones.di.api.ApiModule
import ru.skillbranch.gameofthrones.di.InteractionModule
import ru.skillbranch.gameofthrones.di.RepositoriesModule
import ru.skillbranch.gameofthrones.persistance.GameOfThronesDatabase

class GameOfThronesApplication : Application(), KodeinAware {

    override val kodein: Kodein = Kodein.lazy {
        bind() from instance(GameOfThronesContracts.Server.DEV)

        importOnce(androidXModule(this@GameOfThronesApplication))
        importOnce(DatabaseModule.module)
        importOnce(ApiModule.module)
        importOnce(RepositoriesModule.module)
        importOnce(InteractionModule.module)
    }
}