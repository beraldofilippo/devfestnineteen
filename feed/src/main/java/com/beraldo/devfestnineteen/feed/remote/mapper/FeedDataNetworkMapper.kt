package com.beraldo.devfestnineteen.feed.remote.mapper

import com.beraldo.devfestnineteen.coreutils.safeLet
import com.beraldo.devfestnineteen.feed.data.model.FeedArticleData
import com.beraldo.devfestnineteen.feed.remote.model.ArticleApiModel

internal class FeedDataNetworkMapper :
    FeedRemoteMapper<FeedArticleData, ArticleApiModel> {
    override fun mapTo(input: FeedArticleData?): ArticleApiModel? {
        return input?.let {
            ArticleApiModel(
                input.author,
                input.title,
                input.description,
                input.url,
                input.urlToImage,
                input.publishedAt,
                input.content
            )
        }
    }

    override fun mapFrom(input: ArticleApiModel?): FeedArticleData? {
        return input?.let {
            FeedArticleData(
                generateIdFrom(input),
                input.author,
                input.title,
                input.content,
                input.description,
                input.url,
                input.urlToImage,
                input.publishedAt
            )
        }
    }

    /**
     * API does not serve unique ids :(
     *
     * Generate one given the contents of the object.
     *
     * This is far from ideal, but for the sake of this project it should be enough.
     */
    private fun generateIdFrom(input: ArticleApiModel): Int {
        return safeLet(
            input.content,
            input.publishedAt
        ) { content, date ->
            content.plus(date).hashCode()
        } ?: input.hashCode()
    }
}