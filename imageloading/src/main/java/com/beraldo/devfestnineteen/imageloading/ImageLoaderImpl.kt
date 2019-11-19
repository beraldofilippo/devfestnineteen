package com.beraldo.devfestnineteen.imageloading

import android.widget.ImageView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

internal class ImageLoaderImpl(private val glide: RequestManager) :
    ImageLoader {

    override fun loadHeadlinerImage(imageView: ImageView, urlToImage: String?) {
        val radius = imageView.context.resources
            .getDimensionPixelSize(R.dimen.headliner_image_corner_radius)

        loadImageWithCrossFade(urlToImage, imageView, radius)
    }

    override fun loadItemImage(imageView: ImageView, urlToImage: String?) {
        val radius = imageView.context.resources
            .getDimensionPixelSize(R.dimen.item_image_corner_radius)

        loadImageWithCrossFade(urlToImage, imageView, radius)
    }

    private fun loadImageWithCrossFade(urlToImage: String?, imageView: ImageView, radius: Int) {
        urlToImage?.let {
            glide.load(urlToImage)
                .transform(RoundedCorners(radius))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView)
        }
    }
}
