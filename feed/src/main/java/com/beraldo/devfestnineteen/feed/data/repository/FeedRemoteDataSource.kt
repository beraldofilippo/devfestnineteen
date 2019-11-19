package com.beraldo.devfestnineteen.feed.data.repository

import com.beraldo.devfestnineteen.feed.data.model.FeedArticleData
import io.reactivex.Observable

interface FeedRemoteDataSource {
    fun getFeed(): Observable<List<FeedArticleData?>>
}