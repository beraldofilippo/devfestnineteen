package com.beraldo.devfestnineteen.feed.local.source

import com.beraldo.devfestnineteen.feed.FeedTestData
import com.beraldo.devfestnineteen.feed.data.repository.FeedLocalDataSource
import com.beraldo.devfestnineteen.feed.local.database.FeedDAO
import com.beraldo.devfestnineteen.feed.local.mapper.FeedDataLocalMapper
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class FeedLocalDataSourceImplTest {
    private lateinit var underTest: FeedLocalDataSource

    @Mock
    private lateinit var feedDAO: FeedDAO

    private val feedDataLocalMapper = FeedDataLocalMapper()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        underTest = FeedLocalDataSourceImpl(
            feedDataLocalMapper,
            feedDAO
        )
    }

    @Test
    fun getFeed_success() {
        val localFeed = FeedTestData.getLocalFeed()
        given(feedDAO.getFeed()).willReturn(Observable.just(localFeed))

        underTest.getFeed()
            .test()
            .assertSubscribed()
            .assertValue { feed ->
                localFeed.containsAll(
                    feed.map { feedDataLocalMapper.mapTo(it) }
                )
            }

        verify(feedDAO).getFeed()
    }

    @Test
    fun getFeed_error() {
        val throwable = Throwable("Error!")
        given(feedDAO.getFeed()).willReturn(Observable.error(throwable))

        underTest.getFeed()
            .test()
            .assertSubscribed()
            .assertError(throwable)

        verify(feedDAO).getFeed()
    }
}