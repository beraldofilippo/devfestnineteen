package com.beraldo.devfestnineteen.feed.domain.usecases.base

import io.reactivex.Observable
import io.reactivex.Scheduler

abstract class ObservableUseCase<T, in Input> constructor(
    private val backgroundScheduler: Scheduler,
    private val foregroundScheduler: Scheduler
) {
    protected abstract fun generateObservable(input: Input? = null) : Observable<T>

    fun buildUseCase(input: Input? = null) : Observable<T> =
        generateObservable(input)
            .subscribeOn(backgroundScheduler)
            .observeOn(foregroundScheduler)
}