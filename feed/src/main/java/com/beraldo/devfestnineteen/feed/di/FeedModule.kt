package com.beraldo.devfestnineteen.feed.di

import com.beraldo.devfestnineteen.feed.data.mapper.FeedDomainDataMapper
import com.beraldo.devfestnineteen.feed.data.mapper.FeedDomainMapper
import com.beraldo.devfestnineteen.feed.data.model.FeedArticleData
import com.beraldo.devfestnineteen.feed.data.repository.FeedLocalDataSource
import com.beraldo.devfestnineteen.feed.data.repository.FeedRepositoryImpl
import com.beraldo.devfestnineteen.feed.domain.entities.FeedArticleDomainEntity
import com.beraldo.devfestnineteen.feed.domain.repository.FeedRepository
import com.beraldo.devfestnineteen.feed.domain.usecases.GetFeedUseCase
import com.beraldo.devfestnineteen.feed.domain.usecases.base.ObservableUseCase
import com.beraldo.devfestnineteen.feed.local.FeedDB
import com.beraldo.devfestnineteen.feed.local.mapper.FeedDataLocalMapper
import com.beraldo.devfestnineteen.feed.local.mapper.FeedLocalMapper
import com.beraldo.devfestnineteen.feed.local.model.FeedArticleLocal
import com.beraldo.devfestnineteen.feed.local.source.FeedLocalDataSourceImpl
import com.beraldo.devfestnineteen.feed.presentation.mapper.FeedDomainListMapper
import com.beraldo.devfestnineteen.feed.presentation.mapper.FeedListMapper
import com.beraldo.devfestnineteen.feed.presentation.model.FeedArticle
import com.beraldo.devfestnineteen.feed.presentation.model.resource.ResourceStatusDelegate
import com.beraldo.devfestnineteen.feed.presentation.viewmodels.FeedVM
import com.beraldo.devfestnineteen.feed.remote.api.FeedService
import com.beraldo.devfestnineteen.feed.remote.mapper.FeedDataNetworkMapper
import com.beraldo.devfestnineteen.feed.remote.mapper.FeedRemoteMapper
import com.beraldo.devfestnineteen.feed.remote.model.ArticleApiModel
import com.beraldo.devfestnineteen.feed.remote.source.FeedRemoteDataSourceImpl
import com.beraldo.devfestnineteen.feed.ui.FeedResourceStatusDelegate
import com.beraldo.devfestnineteen.feed.ui.FeedView
import com.beraldo.devfestnineteen.feed.ui.adapters.FeedAdapter
import com.beraldo.devfestnineteen.feed.ui.adapters.HeadlinerViewBinder
import com.beraldo.devfestnineteen.feed.ui.adapters.ItemViewBinder
import com.beraldo.devfestnineteen.feed.ui.adapters.OnItemClickListener
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val feedModule = module {

    single<FeedService> {
        get<Retrofit>().create(FeedService::class.java)
    }

    single<FeedLocalDataSource> {
        FeedLocalDataSourceImpl(
            get(named("FeedDataLocalMapper")),
            get()
        )
    }

    single<FeedLocalMapper<FeedArticleData, FeedArticleLocal>>(named("FeedDataLocalMapper")) {
        FeedDataLocalMapper()
    }

    factory<ResourceStatusDelegate<List<FeedArticle?>>> { (view: FeedView) ->
        FeedResourceStatusDelegate(
            view
        )
    }

    single<com.beraldo.devfestnineteen.feed.data.repository.FeedRemoteDataSource> {
        FeedRemoteDataSourceImpl(
            get(named("FeedDataNetworkMapper")),
            get()
        )
    }

    single<FeedRemoteMapper<FeedArticleData, ArticleApiModel>>(named("FeedDataNetworkMapper")) {
        FeedDataNetworkMapper()
    }

    single(named("FeedAdapter")) { (icl: OnItemClickListener, dataset: List<FeedArticle?>?) ->
        FeedAdapter(get { parametersOf(icl) }, get { parametersOf(icl) }, dataset)
    }

    single { (onClick: OnItemClickListener) -> HeadlinerViewBinder(get(), onClick) }

    single { (onClick: OnItemClickListener) -> ItemViewBinder(get(), onClick) }

    single<FeedDomainMapper<FeedArticleDomainEntity, FeedArticleData>>(named("FeedDomainDataMapper")) {
        FeedDomainDataMapper()
    }

    single<FeedRepository> {
        FeedRepositoryImpl(
            get(named("FeedDomainDataMapper")),
            get(),
            get()
        )
    }

    single<ObservableUseCase<List<FeedArticleDomainEntity?>, GetFeedUseCase.Params>>(named("GetFeedUseCase")) {
        GetFeedUseCase(
            get(),
            get(named("io")),
            get(named("mainThread"))
        )
    }

    viewModel {
        FeedVM(
            get(named("GetFeedUseCase")),
            get(named("FeedDomainListMapper"))
        )
    }

    single<FeedListMapper<FeedArticle?, FeedArticleDomainEntity?>>(named("FeedDomainListMapper")) {
        FeedDomainListMapper()
    }

    single {
        FeedDB.getInstance(androidApplication())
    }

    single { get<FeedDB>().getFeedDAO() }
}