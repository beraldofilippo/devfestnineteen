package com.beraldo.devfestnineteen.feed.domain.repository

import com.beraldo.devfestnineteen.feed.domain.entities.FeedArticleDomainEntity
import io.reactivex.Completable
import io.reactivex.Observable

interface FeedRepository {
    fun getFeed(): Observable<List<FeedArticleDomainEntity?>>

    fun removeFeedArticle(id: String) : Completable

    fun clearFeed(): Completable
}