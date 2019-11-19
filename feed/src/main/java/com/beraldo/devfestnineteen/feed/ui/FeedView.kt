package com.beraldo.devfestnineteen.feed.ui

import com.beraldo.devfestnineteen.feed.presentation.model.FeedArticle

interface FeedView {
    fun showList(show: Boolean, dataset: List<FeedArticle?>? = null)
    fun showError(show: Boolean, message: String? = null)
    fun showLoading(show: Boolean)
}