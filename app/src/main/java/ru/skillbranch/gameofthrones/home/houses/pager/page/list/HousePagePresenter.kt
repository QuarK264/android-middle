package ru.skillbranch.gameofthrones.home.houses.pager.page.list

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import ru.skillbranch.gameofthrones.core.base.RxPresenter
import ru.skillbranch.gameofthrones.core.domain.interaction.GetCharactersInteractor
import ru.skillbranch.gameofthrones.core.extensions.execute
import ru.skillbranch.gameofthrones.core.extensions.mapImmutable
import ru.skillbranch.gameofthrones.home.houses.pager.page.list.model.mapToListView

class HousePagePresenter(
    screen: HousePageScreen,
    private val getCharactersInteractor: GetCharactersInteractor
) : RxPresenter<HousePageScreen>(screen) {

    override fun onSubscribe() {
        super.onSubscribe()

        screen
            .getCharacters
            .flatMapSingle {
                getCharactersInteractor
                    .execute()
                    .map { list ->
                        list.mapImmutable { it.mapToListView() }
                    }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSuccess(screen::displayCharacters)
            }
            .subscribe()
            .addTo(disposable)
    }
}