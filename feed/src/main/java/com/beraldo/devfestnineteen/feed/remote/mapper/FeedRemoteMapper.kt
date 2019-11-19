package com.beraldo.devfestnineteen.feed.remote.mapper

interface FeedRemoteMapper<T, E> {
    fun mapFrom(input: E?): T?
    fun mapTo(input: T?): E?
}