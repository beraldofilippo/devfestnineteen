package com.beraldo.devfestnineteen.detail.presentation.presenters

import com.beraldo.devfestnineteen.detail.presentation.model.ArticleDetail
import com.beraldo.devfestnineteen.detail.ui.DetailView

class DetailPresenter(private val view: DetailView) :
    Presenter<DetailView> {
    override fun startPresenting(article: ArticleDetail) {
        view.showDetail(article)
    }
}