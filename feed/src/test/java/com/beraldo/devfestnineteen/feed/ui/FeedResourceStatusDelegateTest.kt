package com.beraldo.devfestnineteen.feed.ui

import com.beraldo.devfestnineteen.feed.FeedTestData
import com.beraldo.devfestnineteen.feed.presentation.model.FeedArticle
import com.beraldo.devfestnineteen.feed.presentation.model.resource.Resource
import com.beraldo.devfestnineteen.feed.presentation.model.resource.Status
import org.junit.After
import org.junit.Before

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.MockitoAnnotations


@RunWith(JUnit4::class)
class FeedResourceStatusDelegateTest {

    @Mock
    private lateinit var feedView: FeedView

    private lateinit var underTest: FeedResourceStatusDelegate

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        underTest = FeedResourceStatusDelegate(feedView)
    }

    @Test
    fun handlesSuccess() {
        val data = FeedTestData.feed()
        val testResource = Resource<List<FeedArticle?>>(Status.SUCCESS, data, null)

        underTest.handleStatus(testResource)

        verify(feedView).showLoading(false)
        verify(feedView).showError(false)
        verify(feedView).showList(true, testResource.data)
    }

    @Test
    fun handlesError() {
        val errorMessage = "Error happened"
        val testResource = Resource<List<FeedArticle?>>(Status.ERROR, null, errorMessage)

        underTest.handleStatus(testResource)

        verify(feedView).showLoading(false)
        verify(feedView).showList(false)
        verify(feedView).showError(true, testResource.message)
    }

    @Test
    fun handlesLoading() {
        val testResource = Resource<List<FeedArticle?>>(Status.LOADING, null, null)

        underTest.handleStatus(testResource)

        verify(feedView).showError(false)
        verify(feedView).showList(false)
        verify(feedView).showLoading(true)
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(feedView)
    }
}