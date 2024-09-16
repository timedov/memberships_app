package com.example.feed.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import com.example.common.di.ComponentDepsProvider
import com.example.domain.model.PostModel
import com.example.feed.R
import com.example.feed.databinding.FragmentFeedBinding
import com.example.feed.di.DaggerFeedComponent
import com.example.feed.di.FeedComponent
import com.example.feed.presentation.adapter.PostAdapter
import com.example.feed.presentation.model.FeedEvent
import com.example.feed.presentation.model.FeedState
import com.example.ui.base.BaseFragment
import com.example.ui.view.composables.ErrorScreen
import com.example.ui.view.composables.ShowLoading
import com.example.ui.viewmodel.ViewModelProviderFactory
import kotlinx.coroutines.launch
import javax.inject.Inject

class FeedFragment : BaseFragment() {

    private var feedComponent: FeedComponent? = null

    @Inject
    lateinit var vmFactory: ViewModelProviderFactory

    private val viewModel: FeedViewModel by viewModels { vmFactory }

    private var viewBinding: FragmentFeedBinding? = null

    private lateinit var adapter: PostAdapter

    override fun getLayoutId() = R.layout.fragment_feed

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        feedComponent = DaggerFeedComponent
            .factory()
            .create(ComponentDepsProvider.get(requireContext()))
            .apply { inject(this@FeedFragment) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentFeedBinding.inflate(inflater, container, false)
        return viewBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = PostAdapter(
            onPostClick = { postId ->
                viewModel.obtainEvent(FeedEvent.PostClick(postId))
            },
            onTierClick = { tier ->
                viewModel.obtainEvent(FeedEvent.TierClick(tier))
            }
        )

        viewBinding?.apply {
            feedRv.adapter = adapter

            swipeRefreshLayout.setOnRefreshListener {
                viewModel.obtainEvent(FeedEvent.Refresh)
            }
        }

        collectPagingLoadState()

        collectState()
    }

    private fun collectPagingLoadState() {
        lifecycleScope.launch {
            adapter.loadStateFlow.collect { loadState ->
                when (loadState.refresh) {
                    is LoadState.Loading -> showLoading()
                    is LoadState.NotLoading -> Unit
                    is LoadState.Error -> showError()
                }
            }
        }
    }

    private fun collectState() {
        lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                Log.d("FeedFragment", "State: $state")
                when (state) {
                    is FeedState.Loading -> showLoading()
                    is FeedState.Content -> submitData(state.posts)
                    is FeedState.Error -> showError()
                }
            }
        }
    }

    private fun showLoading() {
        viewBinding?.loadingErrorCompose?.setContent {
            ShowLoading(isLoading = true)
        }
        viewBinding?.loadingErrorCompose?.visibility = View.VISIBLE
    }

    private fun submitData(posts: PagingData<PostModel>) {
        adapter.submitData(viewLifecycleOwner.lifecycle, posts)
        viewBinding?.swipeRefreshLayout?.isRefreshing = false
        viewBinding?.loadingErrorCompose?.visibility = View.GONE
    }

    private fun showError() {
        viewBinding?.loadingErrorCompose?.setContent {
            ErrorScreen(
                onRetryClick = { viewModel.obtainEvent(FeedEvent.Refresh) }
            )
        }
        viewBinding?.loadingErrorCompose?.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()

        feedComponent = null
        viewBinding = null
    }
}