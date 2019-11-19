package com.beraldo.devfestnineteen.di

import android.content.Context
import com.beraldo.devfestnineteen.Navigator
import com.beraldo.devfestnineteen.feed.FeedNavigation
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    single(named("io")) { Schedulers.io() }

    single<Scheduler>(named("mainThread")) { AndroidSchedulers.mainThread() }

    single<FeedNavigation> { (context: Context) ->
        Navigator(context)
    }
}