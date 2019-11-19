package com.beraldo.devfestnineteen.detail.ui

import com.beraldo.devfestnineteen.detail.presentation.model.ArticleDetail

interface DetailView {
    fun showDetail(article: ArticleDetail)
}