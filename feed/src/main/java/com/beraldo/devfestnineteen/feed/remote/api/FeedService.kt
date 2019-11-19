package com.beraldo.devfestnineteen.feed.remote.api

import com.beraldo.devfestnineteen.feed.remote.model.ArticleResponseApiModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface FeedService {

    @GET("top-headlines")
    fun getFeed(
        @Query("country") country: String = "gb", // using defaults, for the sake of simplicity
        @Query("sortBy") sortBy: String = "publishedAt" // using defaults, for the sake of simplicity
    ): Observable<ArticleResponseApiModel>
}