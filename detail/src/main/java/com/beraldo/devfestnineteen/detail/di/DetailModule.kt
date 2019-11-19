package com.beraldo.devfestnineteen.detail.di

import com.beraldo.devfestnineteen.detail.presentation.presenters.DetailPresenter
import com.beraldo.devfestnineteen.detail.presentation.presenters.Presenter
import com.beraldo.devfestnineteen.detail.ui.DetailView
import org.koin.dsl.module

val detailModule = module {
    factory<Presenter<DetailView>> { (view: DetailView) ->
        DetailPresenter(view)
    }
}