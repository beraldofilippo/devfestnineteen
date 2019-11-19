package com.beraldo.devfestnineteen.feed.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.toLiveData
import com.beraldo.devfestnineteen.feed.domain.entities.FeedArticleDomainEntity
import com.beraldo.devfestnineteen.feed.domain.usecases.GetFeedUseCase
import com.beraldo.devfestnineteen.feed.domain.usecases.base.ObservableUseCase
import com.beraldo.devfestnineteen.feed.presentation.mapper.FeedListMapper
import com.beraldo.devfestnineteen.feed.presentation.model.FeedArticle
import com.beraldo.devfestnineteen.feed.presentation.model.resource.Resource
import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import io.reactivex.functions.Function

internal class FeedVM(
    private val getFeedUseCase: ObservableUseCase<List<FeedArticleDomainEntity?>, GetFeedUseCase.Params>,
    private val feedArticleDomainMapper: FeedListMapper<FeedArticle?, FeedArticleDomainEntity?>
) : ViewModel() {

    val feed: LiveData<Resource<List<FeedArticle?>>>
        get() =
            getFeedUseCase.buildUseCase(GetFeedUseCase.Params())
                .map { feedArticleDomainMapper.mapFrom(it) }
                .map { Resource.success(it) }
                .startWith(Resource.loading(null))
                .onErrorResumeNext(
                    Function {
                        Observable.just(Resource.error(it.localizedMessage!!, null))
                    }
                )
                .toFlowable(BackpressureStrategy.LATEST)
                .toLiveData()
}