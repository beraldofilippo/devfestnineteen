package com.beraldo.devfestnineteen.feed.ui

import android.graphics.drawable.InsetDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.beraldo.devfestnineteen.coreutils.gone
import com.beraldo.devfestnineteen.coreutils.visible
import com.beraldo.devfestnineteen.feed.FeedNavigation
import com.beraldo.devfestnineteen.feed.R
import com.beraldo.devfestnineteen.feed.presentation.model.FeedArticle
import com.beraldo.devfestnineteen.feed.presentation.model.resource.ResourceStatusDelegate
import com.beraldo.devfestnineteen.feed.presentation.viewmodels.FeedVM
import com.beraldo.devfestnineteen.feed.ui.adapters.OnItemClickListener
import kotlinx.android.synthetic.main.fragment_feed.*
import kotlinx.android.synthetic.main.fragment_feed_error.*
import kotlinx.android.synthetic.main.fragment_feed_loading.*
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named

class FeedFragment : Fragment(), FeedView, OnItemClickListener {

    private lateinit var viewAdapter: RecyclerView.Adapter<*>

    private val viewModel: FeedVM by viewModel()
    private val resourceDelegate: ResourceStatusDelegate<List<FeedArticle?>> by inject {
        parametersOf(this)
    }

    private val navigation: FeedNavigation by inject {
        parametersOf(requireActivity())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(requireActivity())
        val dividerItemDecoration = DividerItemDecoration(
            recycler.context,
            layoutManager.orientation
        )
        dividerItemDecoration.setDrawable(getDividerAndIncreaseHeight())
        recycler.addItemDecoration(dividerItemDecoration)
        recycler.setHasFixedSize(true)
        recycler.layoutManager = layoutManager
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.feed.observe(this, Observer { resource ->
            resourceDelegate.handleStatus(resource)
        })
    }

    override fun onItemClick(item: FeedArticle) {
        navigation.navigateToDetail(item)
    }

    override fun showError(show: Boolean, message: String?) {
        if (show) {
            error.visible()
            message?.let { error_tv.text = it }
        } else {
            error.gone()
        }
    }

    override fun showList(show: Boolean, dataset: List<FeedArticle?>?) {
        if (show) {
            recycler.visible()
            viewAdapter = get(named("FeedAdapter")) { parametersOf(this, dataset) }

            recycler.apply {
                adapter = viewAdapter
            }
        } else {
            recycler.gone()
        }
    }

    override fun showLoading(show: Boolean) {
        if (show) {
            loading.visible()
        } else {
            loading.gone()
        }
    }

    private fun getDividerAndIncreaseHeight(): InsetDrawable {
        val attrs = intArrayOf(android.R.attr.listDivider)

        val a = requireActivity().obtainStyledAttributes(attrs)
        val divider = a.getDrawable(0)
        val insetV = resources.getDimensionPixelSize(R.dimen.feed_divider_inset_vertical)
        val insetH = resources.getDimensionPixelSize(R.dimen.feed_divider_inset_horizontal)
        a.recycle()

        return InsetDrawable(divider, insetH, insetV, insetH, insetV)
    }
}
