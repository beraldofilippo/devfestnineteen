package com.beraldo.devfestnineteen.feed.local.mapper

import com.beraldo.devfestnineteen.feed.data.model.FeedArticleData
import com.beraldo.devfestnineteen.feed.local.model.FeedArticleLocal

internal class FeedDataLocalMapper :
    FeedLocalMapper<FeedArticleData, FeedArticleLocal> {
    override fun mapTo(input: FeedArticleData?): FeedArticleLocal? {
        return input?.let {
            FeedArticleLocal(
                input.article_id,
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

    override fun mapFrom(input: FeedArticleLocal?): FeedArticleData? {
        return input?.let {
            FeedArticleData(
                input.article_id,
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
}