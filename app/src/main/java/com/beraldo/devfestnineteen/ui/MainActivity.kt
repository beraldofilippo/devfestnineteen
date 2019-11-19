package com.beraldo.devfestnineteen.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.beraldo.devfestnineteen.R
import com.beraldo.devfestnineteen.feed.ui.FeedActivity
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        main_container.systemUiVisibility =
                // Tells the system that the window wishes the content to
                // be laid out at the most extreme scenario. See the docs for
                // more information on the specifics
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    // Tells the system that the window wishes the content to
                    // be laid out as if the navigation bar was hidden
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION

        val intent = Intent(this, FeedActivity::class.java)
        startActivity(intent)

        finish()
    }
}
