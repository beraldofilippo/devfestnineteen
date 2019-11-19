package com.beraldo.devfestnineteen.feed.data.repository

import com.beraldo.devfestnineteen.feed.FeedTestData
import com.beraldo.devfestnineteen.feed.data.mapper.FeedDomainDataMapper
import com.beraldo.devfestnineteen.feed.domain.repository.FeedRepository
import io.reactivex.Observable
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class FeedRepositoryImplTest {

    private lateinit var underTest: FeedRepository

    private val feedDomainDataMapper =
        FeedDomainDataMapper()

    @Mock
    private lateinit var localDataSource: FeedLocalDataSource

    @Mock
    private lateinit var remoteDataSource: FeedRemoteDataSource

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        underTest = FeedRepositoryImpl(
            feedDomainDataMapper,
            localDataSource,
            remoteDataSource
        )
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(localDataSource, remoteDataSource)
    }

    @Test
    fun `getFeed() remote call succeeds, chains local call and saves local data`() {
        val feedArticleData = FeedTestData.getFeedArticleData()
        val feedArticleDomain = feedArticleData.map { feedDomainDataMapper.mapFrom(it) }

        given(remoteDataSource.getFeed()).willReturn(Observable.just(feedArticleData))
        given(localDataSource.getFeed()).willReturn(Observable.just(feedArticleData))

        underTest.getFeed().test()
            .assertSubscribed()
            .assertValueCount(2)
            .assertValues(feedArticleDomain, feedArticleDomain)
            .assertComplete()

        verify(localDataSource, times(1)).saveFeedArticle(feedArticleData)
        verify(localDataSource, times(1)).getFeed()
        verify(remoteDataSource, times(1)).getFeed()
    }

    @Test
    fun `getFeed() remote call fails, local call succeeds`() {
        val feedArticleData = FeedTestData.getFeedArticleData()
        val feedArticleDomain = feedArticleData.map { feedDomainDataMapper.mapFrom(it) }

        val throwable = Throwable("Network call error!")
        given(remoteDataSource.getFeed()).willReturn(Observable.error(throwable))
        given(localDataSource.getFeed()).willReturn(Observable.just(feedArticleData))

        underTest.getFeed()
            .test()
            .assertSubscribed()
            .assertValueCount(1)
            .assertValues(feedArticleDomain)
            .assertComplete()

        verify(localDataSource, times(1)).getFeed()
        verify(remoteDataSource, times(1)).getFeed()
    }

    @Test
    fun `getFeed() remote call succeeds, local call fails`() {
        val feedArticleData = FeedTestData.getFeedArticleData()
        val feedArticleDomain = feedArticleData.map { feedDomainDataMapper.mapFrom(it) }

        given(remoteDataSource.getFeed()).willReturn(Observable.just(feedArticleData))
        val throwable = Throwable("Local call error!")
        given(localDataSource.getFeed()).willReturn(Observable.error(throwable))

        underTest.getFeed()
            .test()
            .assertSubscribed()
            .assertValueCount(1)
            .assertValues(feedArticleDomain)
            .assertComplete()

        verify(localDataSource, times(1)).saveFeedArticle(feedArticleData)
        verify(localDataSource, times(1)).getFeed()
        verify(remoteDataSource, times(1)).getFeed()
    }

    @Test
    fun `getFeed() calls fail`() {
        val throwable1 = Throwable("Network call error!")
        given(remoteDataSource.getFeed()).willReturn(Observable.error(throwable1))
        val throwable2 = Throwable("Local call error!")
        given(localDataSource.getFeed()).willReturn(Observable.error(throwable2))

        underTest.getFeed()
            .test()
            .assertSubscribed()
            .assertComplete()

        verify(localDataSource, times(1)).getFeed()
        verify(remoteDataSource, times(1)).getFeed()
    }
}