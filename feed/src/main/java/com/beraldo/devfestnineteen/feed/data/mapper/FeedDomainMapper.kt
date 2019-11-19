package com.beraldo.devfestnineteen.feed.data.mapper

interface FeedDomainMapper<T, E> {
    fun mapFrom(input: E?): T?
    fun mapTo(input: T?): E?
}