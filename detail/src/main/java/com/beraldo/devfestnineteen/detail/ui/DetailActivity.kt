package com.beraldo.devfestnineteen.detail.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.beraldo.devfestnineteen.detail.R
import com.beraldo.devfestnineteen.detail.presentation.model.ArticleDetail
import kotlinx.android.synthetic.main.activity_detail.*

const val TAG = "tag_detail"
const val EXTRA_DETAIL = "extra_detail_arg"

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        detail_container.systemUiVisibility =
                // Tells the system that the window wishes the content to
                // be laid out at the most extreme scenario. See the docs for
                // more information on the specifics
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    // Tells the system that the window wishes the content to
                    // be laid out as if the navigation bar was hidden
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION

        intent?.let { intent ->
            val data = intent.getParcelableExtra<ArticleDetail>(
                EXTRA_DETAIL
            )

            data?.let {
                val detailFragment =
                    DetailFragment.newInstance(it)
                supportFragmentManager.beginTransaction()
                    .add(R.id.detail_content, detailFragment,
                        TAG
                    )
                    .commit()
            } ?: throw RuntimeException("There was no extra passed to this activity!")
        } ?: throw RuntimeException("Intent to this activity was null!")
    }
}
