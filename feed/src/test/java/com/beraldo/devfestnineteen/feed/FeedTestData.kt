package com.beraldo.devfestnineteen.feed

import com.beraldo.devfestnineteen.feed.data.model.FeedArticleData
import com.beraldo.devfestnineteen.feed.domain.entities.FeedArticleDomainEntity
import com.beraldo.devfestnineteen.feed.local.model.FeedArticleLocal
import com.beraldo.devfestnineteen.feed.presentation.model.FeedArticle
import com.beraldo.devfestnineteen.feed.remote.model.ArticleApiModel
import com.beraldo.devfestnineteen.feed.remote.model.ArticleResponseApiModel

object FeedTestData {
    fun feed() = listOf(
        FeedArticle(1010),
        FeedArticle(1203)
    )

    fun getArticle() = ArticleApiModel()

    fun getArticleResponse(articles: ArrayList<ArticleApiModel>) =
        ArticleResponseApiModel("ok", articles.size, articles)

    fun feedArticlesList() = listOf(
        FeedArticleDomainEntity(1, "b", "c"),
        FeedArticleDomainEntity(2, "b", "c")
    )

    fun getFeedArticleData() = listOf(
        FeedArticleData(1, "id_data_1"),
        FeedArticleData(2, "id_data_2")
    )

    fun getLocalFeed() =
        listOf(
            FeedArticleLocal(
                1, "newsId", "newsTitle", null,
                null, null, null, null
            )
        )
}