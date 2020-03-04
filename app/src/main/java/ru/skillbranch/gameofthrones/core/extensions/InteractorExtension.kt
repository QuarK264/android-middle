package ru.skillbranch.gameofthrones.core.extensions

import ru.skillbranch.gameofthrones.core.base.Interactor

fun <T> Interactor<Unit, T>.execute(): T = execute(Unit)