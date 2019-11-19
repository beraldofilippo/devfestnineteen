package com.beraldo.devfestnineteen.feed.data.model

data class FeedArticleData(
    val article_id: Int,
    val author: String? = null,
    val title: String? = null,
    val content: String? = null,
    val description: String? = null,
    val url: String? = null,
    val urlToImage: String? = null,
    val publishedAt: String? = null
)