package com.beraldo.devfestnineteen.feed.ui.adapters

import com.beraldo.devfestnineteen.feed.presentation.model.FeedArticle
import com.beraldo.devfestnineteen.imageloading.ImageLoader

internal class ItemViewBinder(private val imageLoader: ImageLoader,
                              private val onItemClickListener: OnItemClickListener) :
    AdapterViewBinder<FeedAdapter.ItemVH, FeedArticle>() {

    override fun bind(viewHolder: FeedAdapter.ItemVH, item: FeedArticle) {
        viewHolder.title.text = item.title

        imageLoader.loadItemImage(viewHolder.image, item.urlToImage)

        viewHolder.itemView.setOnClickListener {
            onItemClickListener.onItemClick(item)
        }
    }
}