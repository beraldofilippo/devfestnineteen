package com.beraldo.devfestnineteen.feed.domain.usecases

import com.beraldo.devfestnineteen.feed.FeedTestData
import com.beraldo.devfestnineteen.feed.domain.repository.FeedRepository
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
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
class GetFeedUseCaseTest {

    @Mock
    private lateinit var feedRepository: FeedRepository

    private lateinit var underTest: GetFeedUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        underTest = GetFeedUseCase(feedRepository, Schedulers.trampoline(), Schedulers.trampoline())
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(feedRepository)
    }

    @Test
    fun `getFeed() succeeds`() {
        given(feedRepository.getFeed()).willReturn(Observable.just(FeedTestData.feedArticlesList()))

        underTest.buildUseCase(GetFeedUseCase.Params())
            .test().assertSubscribed().assertValue {
                it == FeedTestData.feedArticlesList()
            }.assertComplete()

        verify(feedRepository).getFeed()
    }

    @Test
    fun `getFeed() fails`() {
        val throwable = Throwable("Error!")
        given(feedRepository.getFeed()).willReturn(Observable.error(throwable))

        underTest.buildUseCase(GetFeedUseCase.Params())
            .test()
            .assertSubscribed()
            .assertError(throwable)

        verify(feedRepository).getFeed()
    }
}