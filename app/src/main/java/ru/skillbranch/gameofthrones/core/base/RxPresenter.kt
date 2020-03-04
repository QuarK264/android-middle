package ru.skillbranch.gameofthrones.core.base

import io.reactivex.disposables.CompositeDisposable

abstract class RxPresenter<out Screen>(screen: Screen) : Presenter<Screen>(screen) {

    lateinit var disposable: CompositeDisposable
        private set

    override fun onSubscribe() {
        disposable = CompositeDisposable()
    }

    override fun onUnsubscribe() {
        disposable.dispose()
    }
}
