package com.beraldo.devfestnineteen.feed.data.repository

import com.beraldo.devfestnineteen.feed.data.model.FeedArticleData
import io.reactivex.Completable
import io.reactivex.Observable

interface FeedLocalDataSource {
    fun getFeed(): Observable<List<FeedArticleData?>>

    fun saveFeedArticle(feed: List<FeedArticleData?>)

    fun clearFeed(): Completable

    fun removeFeedArticle(id: String): Completable
}