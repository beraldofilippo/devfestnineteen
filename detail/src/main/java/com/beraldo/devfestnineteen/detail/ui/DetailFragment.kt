package com.beraldo.devfestnineteen.detail.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.beraldo.devfestnineteen.detail.R
import com.beraldo.devfestnineteen.detail.presentation.model.ArticleDetail
import com.beraldo.devfestnineteen.detail.presentation.presenters.Presenter
import com.beraldo.devfestnineteen.imageloading.ImageLoader
import kotlinx.android.synthetic.main.detail_fragment.*
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.parameter.parametersOf

private const val ARG_FEED_ARTICLE = "arg_feed_article"

class DetailFragment : Fragment(), KoinComponent, DetailView {

    private val imageLoaderUtil: ImageLoader by inject()

    private val presenter: Presenter<DetailView> by inject {
        parametersOf(this)
    }

    companion object {
        fun newInstance(feed: ArticleDetail) = DetailFragment().apply {
            arguments = bundleOf(Pair(ARG_FEED_ARTICLE, feed))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getParcelable<ArticleDetail>(
            ARG_FEED_ARTICLE
        )?.let {
            presenter.startPresenting(it)
        }
    }

    override fun showDetail(article: ArticleDetail) {
        article.apply {
            detail_title.text = this.title
            detail_content.text = this.content
            detail_timestamp.text = this.publishedAt

            imageLoaderUtil.loadHeadlinerImage(detail_image, this.urlToImage)
        }
    }
}