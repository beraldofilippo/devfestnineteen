package com.beraldo.devfestnineteen.feed.local.mapper

interface FeedLocalMapper<T, E> {
    fun mapFrom(input: E?): T?
    fun mapTo(input: T?): E?
}