package com.beraldo.devfestnineteen.feed.presentation.mapper

import com.beraldo.devfestnineteen.feed.domain.entities.FeedArticleDomainEntity
import com.beraldo.devfestnineteen.feed.presentation.model.FeedArticle

internal class FeedDomainListMapper :
    FeedListMapper<FeedArticle?, FeedArticleDomainEntity?> {

    override fun mapFrom(input: List<FeedArticleDomainEntity?>): List<FeedArticle?> {
        val result = ArrayList<FeedArticle>()

        input.filterNotNull().forEach {
            result.add(
                FeedArticle(
                    it.article_id,
                    it.author,
                    it.title,
                    it.description,
                    it.url,
                    it.urlToImage,
                    it.publishedAt,
                    it.content
                )
            )
        }

        return result
    }


    override fun mapTo(input: List<FeedArticle?>): List<FeedArticleDomainEntity?> {
        val result = ArrayList<FeedArticleDomainEntity>()

        input.filterNotNull().forEach {
            result.add(
                FeedArticleDomainEntity(
                    it.article_id,
                    it.author,
                    it.title,
                    it.description,
                    it.url,
                    it.urlToImage,
                    it.publishedAt,
                    it.content
                )
            )
        }

        return result
    }
}