package ru.skillbranch.gameofthrones.core.extensions

import io.reactivex.subjects.Subject

fun Subject<Unit>.onNext() = onNext(Unit)