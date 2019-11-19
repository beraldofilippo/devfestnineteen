package com.beraldo.devfestnineteen.feed.ui

import com.beraldo.devfestnineteen.feed.presentation.model.FeedArticle
import com.beraldo.devfestnineteen.feed.presentation.model.resource.Resource
import com.beraldo.devfestnineteen.feed.presentation.model.resource.ResourceStatusDelegate

internal class FeedResourceStatusDelegate(private val view: FeedView) :
    ResourceStatusDelegate<List<FeedArticle?>>() {

    override fun handleSuccess(resource: Resource<List<FeedArticle?>>) {
        view.showLoading(false)
        view.showError(false)
        view.showList(true, resource.data)
    }

    override fun handleError(resource: Resource<List<FeedArticle?>>) {
        view.showLoading(false)
        view.showList(false)
        view.showError(true, resource.message)
    }

    override fun handleLoading(resource: Resource<List<FeedArticle?>>) {
        view.showError(false)
        view.showList(false)
        view.showLoading(true)
    }
}