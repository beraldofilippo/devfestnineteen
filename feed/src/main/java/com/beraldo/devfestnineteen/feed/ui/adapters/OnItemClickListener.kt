package com.beraldo.devfestnineteen.feed.ui.adapters

import com.beraldo.devfestnineteen.feed.presentation.model.FeedArticle

interface OnItemClickListener {
    fun onItemClick(item: FeedArticle)
}