package com.beraldo.devfestnineteen.imageloading

import android.widget.ImageView

interface ImageLoader {
    fun loadHeadlinerImage(imageView: ImageView, urlToImage: String?)
    fun loadItemImage(imageView: ImageView, urlToImage: String?)
}