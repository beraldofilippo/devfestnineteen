package com.beraldo.devfestnineteen.detail.presentation.presenters

import com.beraldo.devfestnineteen.detail.presentation.model.ArticleDetail
import com.beraldo.devfestnineteen.detail.ui.DetailView

interface Presenter<V : DetailView> {
    fun startPresenting(article: ArticleDetail)
}