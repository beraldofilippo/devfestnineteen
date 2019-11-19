package com.beraldo.devfestnineteen.feed.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.beraldo.devfestnineteen.feed.R
import kotlinx.android.synthetic.main.activity_feed.*
import java.lang.RuntimeException

class FeedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)

        feed_container.systemUiVisibility =
                // Tells the system that the window wishes the content to
                // be laid out at the most extreme scenario. See the docs for
                // more information on the specifics
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    // Tells the system that the window wishes the content to
                    // be laid out as if the navigation bar was hidden
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION

        throw RuntimeException("You need to get a newsapi.org api key and put it in NetworkModule.") // todo remove me when you have one.
    }
}
