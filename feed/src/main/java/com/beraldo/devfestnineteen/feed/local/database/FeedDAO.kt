package com.beraldo.devfestnineteen.feed.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.beraldo.devfestnineteen.feed.local.model.FeedArticleLocal
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface FeedDAO {
    @Query("SELECT * FROM feed_articles")
    fun getFeed(): Observable<List<FeedArticleLocal>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveFeedArticle(feedArticle: FeedArticleLocal)

    @Query("DELETE from feed_articles")
    fun clearFeed(): Completable

    @Query("DELETE from feed_articles WHERE article_id = :idToRemove")
    fun removeFeedArticle(idToRemove: String): Completable
}