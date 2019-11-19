package com.beraldo.devfestninenteen.network.di

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.RuntimeException

val networkModule = module {
    single { OkHttpClient.Builder() }

    single {
        Interceptor { chain ->
            val original = chain.request()
            val originalHttpUrl = original.url()

            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("apiKey", "<The API key>") //todo the api key
                .build()

            // Request customization: add request headers
            val requestBuilder = original.newBuilder()
                .url(url)

            val request = requestBuilder.build()
            chain.proceed(request)
        }
    }

    single {
        Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://newsapi.org/v2/")
            .client(
                get<OkHttpClient.Builder>()
                    .addInterceptor(get())
                    .build()
            )
            .build()
    }
}