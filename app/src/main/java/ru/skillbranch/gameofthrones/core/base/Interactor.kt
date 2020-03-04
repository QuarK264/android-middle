package ru.skillbranch.gameofthrones.core.base

interface Interactor<in Request, out Response> {
    fun execute(request: Request): Response
}