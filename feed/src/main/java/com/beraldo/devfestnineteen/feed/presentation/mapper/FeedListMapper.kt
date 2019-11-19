package com.beraldo.devfestnineteen.feed.presentation.mapper

interface FeedListMapper<T, E> {
    fun mapFrom(input: List<E?>): List<T?>
    fun mapTo(input: List<T?>): List<E?>
}