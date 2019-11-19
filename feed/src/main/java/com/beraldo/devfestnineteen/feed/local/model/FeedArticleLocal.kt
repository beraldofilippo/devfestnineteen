package com.beraldo.devfestnineteen.feed.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "feed_articles")
data class FeedArticleLocal(
    @PrimaryKey @ColumnInfo(name="article_id") val article_id: Int,
    @ColumnInfo(name="author") val author: String? = null,
    @ColumnInfo(name="title") val title: String? = null,
    @ColumnInfo(name="content") val content: String? = null,
    @ColumnInfo(name="description") val description: String? = null,
    @ColumnInfo(name="url") val url: String? = null,
    @ColumnInfo(name="urlToImage") val urlToImage: String? = null,
    @ColumnInfo(name="publishedAt") val publishedAt: String? = null
)