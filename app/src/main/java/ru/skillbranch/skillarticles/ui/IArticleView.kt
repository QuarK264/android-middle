package ru.skillbranch.skillarticles.ui

interface IArticleView {

    /**
     * Отрисовать все вхождения поискового запрос в контент (spannable)
     */
    fun renderSearchResult(searchResult: List<Pair<Int, Int>>)

    /**
     * Отрисовать текущее положения поиска и перевести фокус на него (spannable)
     */
    fun renderSearchPosition(searchPosition: Int)

    /**
     * Очистить результаты поиска (удалить все spannable)
     */
    fun clearSearchResult()

    /**
     * Показать search bar
     */
    fun showSearchBar()
    /**
     * Скрыть searchbar
     */
    fun hideSearchBar()
}