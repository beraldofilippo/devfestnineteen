package com.beraldo.devfestnineteen.feed.data.repository

import com.beraldo.devfestnineteen.feed.data.mapper.FeedDomainMapper
import com.beraldo.devfestnineteen.feed.data.model.FeedArticleData
import com.beraldo.devfestnineteen.feed.domain.entities.FeedArticleDomainEntity
import com.beraldo.devfestnineteen.feed.domain.repository.FeedRepository
import io.reactivex.Completable
import io.reactivex.Observable

internal class FeedRepositoryImpl(
    private val feedArticleDomainDataMapper: FeedDomainMapper<FeedArticleDomainEntity, FeedArticleData>,
    private val localDataSource: FeedLocalDataSource,
    private val remoteDataSource: FeedRemoteDataSource
) : FeedRepository {
    override fun getFeed(): Observable<List<FeedArticleDomainEntity?>> {
        val localDataSourceObservable =
            localDataSource.getFeed()
                .map { result -> result.map { feedArticleDomainDataMapper.mapFrom(it) } }
                .onErrorResumeNext(Observable.empty())

        return remoteDataSource.getFeed()
            .map { result ->
                localDataSource.saveFeedArticle(result)
                result.map { feedArticleDomainDataMapper.mapFrom(it) }
            }.onErrorResumeNext(Observable.empty())
            .concatWith(localDataSourceObservable)
    }

    override fun removeFeedArticle(id: String): Completable {
        return localDataSource.removeFeedArticle(id)
    }

    override fun clearFeed(): Completable {
        return localDataSource.clearFeed()
    }
}