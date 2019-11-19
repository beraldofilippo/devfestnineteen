package com.beraldo.devfestnineteen.feed.remote.source

import com.beraldo.devfestnineteen.feed.data.model.FeedArticleData
import com.beraldo.devfestnineteen.feed.data.repository.FeedRemoteDataSource
import com.beraldo.devfestnineteen.feed.remote.api.FeedService
import com.beraldo.devfestnineteen.feed.remote.mapper.FeedRemoteMapper
import com.beraldo.devfestnineteen.feed.remote.model.ArticleApiModel
import io.reactivex.Observable

internal class FeedRemoteDataSourceImpl(
    private val feedArticleDataNetworkMapper: FeedRemoteMapper<FeedArticleData, ArticleApiModel>,
    private val feedService: FeedService
) : FeedRemoteDataSource {

    override fun getFeed(): Observable<List<FeedArticleData?>> {
        return feedService.getFeed()
            .map { response ->
                response.articles?.map {
                    feedArticleDataNetworkMapper.mapFrom(it)
                }!!
            }
    }
}