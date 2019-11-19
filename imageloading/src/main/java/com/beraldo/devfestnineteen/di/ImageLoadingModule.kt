package com.beraldo.devfestnineteen.di

import com.beraldo.devfestnineteen.imageloading.ImageLoader
import com.beraldo.devfestnineteen.imageloading.ImageLoaderImpl
import com.bumptech.glide.Glide
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val imageLoadingModule = module {
    single<ImageLoader> {
        ImageLoaderImpl(Glide.with(androidContext()))
    }
}