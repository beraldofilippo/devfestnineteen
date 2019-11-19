package com.beraldo.devfestnineteen.feed.remote.source

import com.beraldo.devfestnineteen.feed.FeedTestData
import com.beraldo.devfestnineteen.feed.data.repository.FeedRemoteDataSource
import com.beraldo.devfestnineteen.feed.remote.api.FeedService
import com.beraldo.devfestnineteen.feed.remote.mapper.FeedDataNetworkMapper
import io.reactivex.Observable
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class FeedRemoteDataSourceImplTest {
    @Mock
    private lateinit var feedService: FeedService

    private val feedDataNetworkMapper =
        FeedDataNetworkMapper()

    private lateinit var underTest : FeedRemoteDataSource

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        underTest = FeedRemoteDataSourceImpl(feedDataNetworkMapper, feedService)
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(feedService)
    }

    @Test
    fun getFeed_success() {
        given(feedService.getFeed()).willReturn(
            Observable.just(
                FeedTestData.getArticleResponse(
                    arrayListOf(
                        FeedTestData.getArticle()
                    )
                )
            )
        )

        underTest.getFeed()
            .test()
            .assertSubscribed()
            .assertValue { result ->
                result == arrayListOf(feedDataNetworkMapper.mapFrom(FeedTestData.getArticle()))
            }.assertComplete()

        verify(feedService).getFeed()
    }

    @Test
    fun getFeed_error() {
        val throwable = Throwable("Error!")
        given(feedService.getFeed()).willReturn(Observable.error(throwable))

        underTest.getFeed()
            .test()
            .assertSubscribed()
            .assertError(throwable)

        verify(feedService).getFeed()
    }
}