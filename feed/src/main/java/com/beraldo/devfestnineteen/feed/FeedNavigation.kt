package com.beraldo.devfestnineteen.feed

import com.beraldo.devfestnineteen.feed.presentation.model.FeedArticle

interface FeedNavigation {
    fun navigateToDetail(feedArticle: FeedArticle)
}