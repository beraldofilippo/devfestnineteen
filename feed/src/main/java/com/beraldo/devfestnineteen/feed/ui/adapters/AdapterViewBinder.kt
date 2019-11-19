package com.beraldo.devfestnineteen.feed.ui.adapters

import androidx.recyclerview.widget.RecyclerView
import com.beraldo.devfestnineteen.feed.presentation.model.FeedArticle

abstract class AdapterViewBinder<VH : RecyclerView.ViewHolder, I : FeedArticle> {
    abstract fun bind(viewHolder: VH, item: I)
}