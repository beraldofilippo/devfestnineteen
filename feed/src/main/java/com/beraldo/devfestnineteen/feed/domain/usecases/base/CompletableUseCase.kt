package com.beraldo.devfestnineteen.feed.domain.usecases.base

import io.reactivex.Completable
import io.reactivex.Scheduler

abstract class CompletableUseCase<in Input> constructor(
    private val backgroundScheduler: Scheduler,
    private val foregroundScheduler: Scheduler
) {
    protected abstract fun generateObservable(input: Input? = null): Completable

    fun buildUseCase(input: Input? = null): Completable =
        generateObservable(input)
            .subscribeOn(backgroundScheduler)
            .observeOn(foregroundScheduler)
}