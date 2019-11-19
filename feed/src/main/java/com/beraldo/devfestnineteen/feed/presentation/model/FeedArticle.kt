package com.beraldo.devfestnineteen.feed.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FeedArticle(
    val article_id: Int,
    val author: String? = null,
    val title: String? = null,
    val description: String? = null,
    val url: String? = null,
    val urlToImage: String? = null,
    val publishedAt: String? = null,
    val content: String? = null
) : Parcelable