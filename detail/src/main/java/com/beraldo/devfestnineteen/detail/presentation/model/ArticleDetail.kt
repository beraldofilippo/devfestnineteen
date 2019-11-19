package com.beraldo.devfestnineteen.detail.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ArticleDetail(
    val title: String,
    val content: String,
    val publishedAt: String,
    val urlToImage: String
) : Parcelable