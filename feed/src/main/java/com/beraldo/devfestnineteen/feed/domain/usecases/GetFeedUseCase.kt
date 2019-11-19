package com.beraldo.devfestnineteen.feed.domain.usecases

import com.beraldo.devfestnineteen.feed.domain.entities.FeedArticleDomainEntity
import com.beraldo.devfestnineteen.feed.domain.repository.FeedRepository
import com.beraldo.devfestnineteen.feed.domain.usecases.base.ObservableUseCase
import io.reactivex.Observable
import io.reactivex.Scheduler

class GetFeedUseCase(
    private val feedRepository: FeedRepository,
    backgroundScheduler: Scheduler,
    foregroundScheduler: Scheduler
) : ObservableUseCase<List<FeedArticleDomainEntity?>, GetFeedUseCase.Params>(
    backgroundScheduler,
    foregroundScheduler
) {

    override fun generateObservable(input: Params?): Observable<List<FeedArticleDomainEntity?>> {
        return input?.let { feedRepository.getFeed() }
            ?: throw IllegalArgumentException("Input params cannot be null.")
    }

    data class Params(val a : Boolean = true)
}