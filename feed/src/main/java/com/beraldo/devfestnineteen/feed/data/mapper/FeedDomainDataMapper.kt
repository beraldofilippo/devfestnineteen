package com.beraldo.devfestnineteen.feed.data.mapper

import com.beraldo.devfestnineteen.feed.data.model.FeedArticleData
import com.beraldo.devfestnineteen.feed.domain.entities.FeedArticleDomainEntity

internal class FeedDomainDataMapper :
    FeedDomainMapper<FeedArticleDomainEntity, FeedArticleData> {

    override fun mapFrom(input: FeedArticleData?): FeedArticleDomainEntity? {
        return input?.let {
            FeedArticleDomainEntity(
                input.article_id,
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

    override fun mapTo(input: FeedArticleDomainEntity?): FeedArticleData? {
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