package com.beraldo.devfestnineteen.feed.remote.model

data class ArticleResponseApiModel(
    val status: String,
    val totalResults: Int = 0,
    val articles: ArrayList<ArticleApiModel>? = null
)