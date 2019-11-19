package com.beraldo.devfestnineteen.feed.local.source

import com.beraldo.devfestnineteen.feed.data.model.FeedArticleData
import com.beraldo.devfestnineteen.feed.data.repository.FeedLocalDataSource
import com.beraldo.devfestnineteen.feed.local.database.FeedDAO
import com.beraldo.devfestnineteen.feed.local.mapper.FeedLocalMapper
import com.beraldo.devfestnineteen.feed.local.model.FeedArticleLocal
import io.reactivex.Completable
import io.reactivex.Observable

class FeedLocalDataSourceImpl(
    private val feedArticleDataLocalMapper: FeedLocalMapper<FeedArticleData, FeedArticleLocal>,
    private val feedDAO: FeedDAO
) : FeedLocalDataSource {
    override fun getFeed(): Observable<List<FeedArticleData?>> {
        return feedDAO.getFeed()
            .map { localFeed ->
                localFeed.map { feedArticleDataLocalMapper.mapFrom(it) }
            }
    }

    override fun saveFeedArticle(feed: List<FeedArticleData?>) {
        feed.map { feedArticleData ->
            feedArticleDataLocalMapper.mapTo(feedArticleData)?.let { feedDAO.saveFeedArticle(it) }
        }
    }

    override fun removeFeedArticle(id: String): Completable {
        return feedDAO.removeFeedArticle(id)
    }

    override fun clearFeed(): Completable {
        return feedDAO.clearFeed()
    }
}