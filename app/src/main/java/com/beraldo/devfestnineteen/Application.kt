package com.beraldo.devfestnineteen

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.beraldo.devfestnineteen.detail.di.detailModule
import com.beraldo.devfestnineteen.di.appModule
import com.beraldo.devfestnineteen.di.imageLoadingModule
import com.beraldo.devfestninenteen.network.di.networkModule
import com.beraldo.devfestnineteen.feed.di.feedModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/** todo use a preference and settings for dark mode
 * https://medium.com/androiddevelopers/appcompat-v23-2-daynight-d10f90c83e94
 * */
class Application : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            // use AndroidLogger as Koin Logger - default Level.INFO
            androidLogger()

            // Android context
            androidContext(this@Application)

            // modules
            modules(
                listOf(
                    appModule,
                    feedModule,
                    detailModule,
                    networkModule,
                    imageLoadingModule
                )
            )
        }
    }
}