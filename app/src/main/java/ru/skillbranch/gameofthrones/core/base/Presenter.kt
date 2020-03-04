package ru.skillbranch.gameofthrones.core.base

abstract class Presenter<out Screen>(protected val screen: Screen) : ScreenObserver {

    override fun subscribe() {
        if (subscribed) {
            throw IllegalStateException("Presenter is already subscribed.")
        }

        subscribed = true

        onSubscribe()
    }

    override fun unsubscribe() {
        if (!subscribed) {
            throw IllegalStateException("Presenter is not subscribed.")
        }

        subscribed = false

        onUnsubscribe()
    }

    protected open fun onSubscribe() {}

    protected open fun onUnsubscribe() {}

    private var subscribed = false
}
