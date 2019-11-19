package com.beraldo.devfestnineteen.feed.presentation.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.beraldo.devfestnineteen.feed.FeedTestData
import com.beraldo.devfestnineteen.feed.domain.entities.FeedArticleDomainEntity
import com.beraldo.devfestnineteen.feed.domain.usecases.GetFeedUseCase
import com.beraldo.devfestnineteen.feed.domain.usecases.base.ObservableUseCase
import com.beraldo.devfestnineteen.feed.presentation.mapper.FeedDomainListMapper
import com.beraldo.devfestnineteen.feed.presentation.model.resource.Status
import io.reactivex.Observable
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class FeedVMTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var feedUseCase: ObservableUseCase<List<FeedArticleDomainEntity?>, GetFeedUseCase.Params>

    private lateinit var underTest: FeedVM
    private val mapper = FeedDomainListMapper()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        underTest = FeedVM(feedUseCase, mapper)
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(feedUseCase)
    }

    @Test
    fun `getFeed() succeeds`() {
        val params = GetFeedUseCase.Params()
        given(feedUseCase.buildUseCase(params))
            .willReturn(Observable.just(FeedTestData.feedArticlesList()))

        val mappedResult = mapper.mapFrom(FeedTestData.feedArticlesList())

        val feedResource = underTest.feed

        feedResource.observeForever {}

        assertTrue(
            feedResource.value?.status == Status.SUCCESS
                    && feedResource.value?.data == mappedResult
        )

        verify(feedUseCase).buildUseCase(params)
    }

    @Test
    fun `getFeed() fails`() {
        val errorMessage = "GetFeedUseCase error!"
        val throwable = Throwable(errorMessage)
        val params = GetFeedUseCase.Params()
        given(feedUseCase.buildUseCase(params))
            .willReturn(Observable.error(throwable))

        val feedResource = underTest.feed

        feedResource.observeForever {}

        assertTrue(
            feedResource.value?.status == Status.ERROR
                    && feedResource.value?.message == errorMessage
        )

        verify(feedUseCase).buildUseCase(params)
    }
}