package com.beraldo.devfestnineteen

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import com.beraldo.devfestnineteen.detail.presentation.model.ArticleDetail
import com.beraldo.devfestnineteen.detail.ui.DetailActivity
import com.beraldo.devfestnineteen.detail.ui.EXTRA_DETAIL
import com.beraldo.devfestnineteen.feed.FeedNavigation
import com.beraldo.devfestnineteen.feed.presentation.model.FeedArticle

class Navigator(private val context: Context) : FeedNavigation {

    override fun navigateToDetail(feedArticle: FeedArticle) {
        val intent = Intent(context, DetailActivity::class.java).apply {
            putExtra(EXTRA_DETAIL, mapFeedToDetail(feedArticle))
        }
        startActivity(context, intent, null)
    }

    private fun mapFeedToDetail(input: FeedArticle) =
        ArticleDetail(
            input.title ?: "",
            input.content ?: "",
            input.publishedAt ?: "",
            input.urlToImage ?: ""
        )
}