package ru.skillbranch.gameofthrones

import android.app.Application
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule

class GameOfThronesApplication : Application(), KodeinAware {

    override val kodein: Kodein = Kodein.lazy {
        importOnce(androidXModule(this@GameOfThronesApplication))
    }
}