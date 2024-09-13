package com.example.feed.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.common.di.ComponentDepsProvider
import com.example.common.utils.observe
import com.example.domain.model.Tier
import com.example.feed.R
import com.example.feed.databinding.FragmentFeedBinding
import com.example.feed.di.DaggerFeedComponent
import com.example.feed.di.FeedComponent
import com.example.feed.presentation.adapter.PostAdapter
import com.example.feed.presentation.model.FeedEvent
import com.example.feed.presentation.model.FeedState
import com.example.ui.base.BaseFragment
import com.example.ui.viewmodel.ViewModelProviderFactory
import javax.inject.Inject

class FeedFragment : BaseFragment() {

    private var feedComponent: FeedComponent? = null

    @Inject
    lateinit var vmFactory: ViewModelProviderFactory

    private val viewModel: FeedViewModel by viewModels { vmFactory }

    private var viewBinding: FragmentFeedBinding? = null

    private lateinit var postAdapter: PostAdapter

    override fun getLayoutId() = R.layout.fragment_feed

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        feedComponent = DaggerFeedComponent
            .factory()
            .create(ComponentDepsProvider.get(requireContext()))
            .apply { inject(this@FeedFragment) }

        postAdapter = PostAdapter(
            onPostClick = ::onPostClicked,
            onTierClick = ::onTierClicked
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding = FragmentFeedBinding.bind(view)

        viewBinding?.feedRv?.adapter = postAdapter

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.uiState.observe(this) { state ->
            when (state) {
                is FeedState.Loading -> showLoading()
                is FeedState.Content -> showContent(state)
                is FeedState.Error -> showError(state.error)
                is FeedState.Initial -> Unit
            }
        }
    }

    private fun onPostClicked(id: Long) {
        viewModel.obtainEvent(FeedEvent.PostClick(id))
    }

    private fun onTierClicked(tier: Tier) {
        viewModel.obtainEvent(FeedEvent.TierClick(tier))
    }

    private fun showLoading() {
        // Show loading
    }

    private fun showContent(state: FeedState.Content) {
        postAdapter.submitData(lifecycle, state.posts)
    }

    private fun showError(error: Throwable) {
        // Show error
    }

    override fun onDestroyView() {
        super.onDestroyView()

        viewBinding = null
        feedComponent = null
    }
}