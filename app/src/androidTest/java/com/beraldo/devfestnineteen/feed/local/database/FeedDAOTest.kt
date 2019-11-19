package com.beraldo.devfestnineteen.feed.local.database

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.beraldo.devfestnineteen.feed.local.FeedDB
import com.beraldo.devfestnineteen.feed.local.FeedTestData
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class FeedDAOTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var underTest: FeedDAO

    private lateinit var db: FeedDB

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        db = Room.inMemoryDatabaseBuilder(
            context, FeedDB::class.java
        ).allowMainThreadQueries().build()

        underTest = db.getFeedDAO()
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        underTest.clearFeed().subscribe()
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeFeedAndReadFeedList() {
        val feed = FeedTestData.getLocalFeed()

        underTest.saveFeedArticle(feed[0])

        underTest.getFeed()
            .test()
            .assertValue { result ->
                result.containsAll(feed)
                        && result.size == feed.size
            }
            .assertNotComplete()
    }

    @Test
    fun clearsFeed() {
        val feed = FeedTestData.getLocalFeed()

        underTest.saveFeedArticle(feed[0])

        underTest.clearFeed().subscribe()

        underTest.getFeed()
            .test()
            .assertValue { result ->
                result.isEmpty()
            }
            .assertNotComplete()
    }
}