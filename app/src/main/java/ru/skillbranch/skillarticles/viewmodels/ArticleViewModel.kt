package ru.skillbranch.skillarticles.viewmodels

import androidx.lifecycle.LiveData
import ru.skillbranch.skillarticles.data.ArticleData
import ru.skillbranch.skillarticles.data.ArticlePersonalInfo

class ArticleViewModel(params: String) : IArticleViewModel {
    override fun getArticleContent(): LiveData<List<Any>?> {
        TODO("Not yet implemented")
    }

    override fun getArticleData(): LiveData<ArticleData?> {
        TODO("Not yet implemented")
    }

    override fun getArticlePersonalInfo(): LiveData<ArticlePersonalInfo?> {
        TODO("Not yet implemented")
    }

    override fun handleNightMode() {
        TODO("Not yet implemented")
    }

    override fun handleUpText() {
        TODO("Not yet implemented")
    }

    override fun handleDownText() {
        TODO("Not yet implemented")
    }

    override fun handleBookmark() {
        TODO("Not yet implemented")
    }

    override fun handleLike() {
        TODO("Not yet implemented")
    }

    override fun handleShare() {
        TODO("Not yet implemented")
    }

    override fun handleToggleMenu() {
        TODO("Not yet implemented")
    }

    override fun handleSearchMode(isSearch: Boolean) {
        TODO("Not yet implemented")
    }

    override fun handleSearch(query: String?) {
        TODO("Not yet implemented")
    }

}