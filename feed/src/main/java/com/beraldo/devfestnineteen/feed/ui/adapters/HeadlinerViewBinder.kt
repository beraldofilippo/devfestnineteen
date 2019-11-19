package com.beraldo.devfestnineteen.feed.ui.adapters

import com.beraldo.devfestnineteen.feed.presentation.model.FeedArticle
import com.beraldo.devfestnineteen.feed.ui.adapters.FeedAdapter.*
import com.beraldo.devfestnineteen.imageloading.ImageLoader

internal class HeadlinerViewBinder(private val imageLoader: ImageLoader,
                                   private val onItemClickListener: OnItemClickListener) :
    AdapterViewBinder<HeadlinerVH, FeedArticle>() {

    override fun bind(viewHolder: HeadlinerVH, item: FeedArticle) {
        viewHolder.title.text = item.title

        imageLoader.loadHeadlinerImage(viewHolder.image, item.urlToImage)

        viewHolder.itemView.setOnClickListener {
            onItemClickListener.onItemClick(item)
        }
    }
}