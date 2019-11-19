package com.beraldo.devfestnineteen.feed.local

import com.beraldo.devfestnineteen.feed.local.model.FeedArticleLocal

object FeedTestData {
    fun getLocalFeed() =
        listOf(
            FeedArticleLocal(
                1, "newsId", "newsTitle", null,
                null, null, null, null
            )
        )
}