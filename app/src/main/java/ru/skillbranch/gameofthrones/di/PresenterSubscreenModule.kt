package ru.skillbranch.gameofthrones.di

import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.contexted
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import ru.skillbranch.gameofthrones.core.base.ScreenObserver
import ru.skillbranch.gameofthrones.home.houses.pager.page.list.HousePagePresenter
import ru.skillbranch.gameofthrones.home.houses.pager.page.list.HousePageScreen

object PresenterSubscreenModule {
    val module = Kodein.Module("PresenterSubscreenModule") {
        bind<ScreenObserver>() with contexted<HousePageScreen>().provider {
            HousePagePresenter(context, instance())
        }
    }
}
