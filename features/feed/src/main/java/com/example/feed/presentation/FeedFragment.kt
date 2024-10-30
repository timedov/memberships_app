package com.example.feed.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.paging.map
import com.example.common.di.ComponentDepsProvider
import com.example.domain.model.PostWithStatsDomainModel
import com.example.feed.R
import com.example.feed.databinding.FragmentFeedBinding
import com.example.feed.di.DaggerFeedComponent
import com.example.feed.presentation.model.FeedEvent
import com.example.ui.base.BaseFragment
import com.example.ui.utils.toUiModel
import com.example.ui.view.adapters.PostLoadStateAdapter
import com.example.ui.view.adapters.PostPagingDataAdapter
import com.example.ui.view.composables.LoadingScreen
import com.example.ui.viewmodel.ViewModelProviderFactory
import kotlinx.coroutines.launch
import javax.inject.Inject

class FeedFragment : BaseFragment() {

    @Inject
    lateinit var vmFactory: ViewModelProviderFactory

    private val viewModel: FeedViewModel by viewModels { vmFactory }

    private var _viewBinding: FragmentFeedBinding? = null
    private val viewBinding get() = _viewBinding!!

    private var adapter: PostPagingDataAdapter? = null

    override fun getLayoutId() = R.layout.fragment_feed

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerFeedComponent.factory()
            .create(ComponentDepsProvider.get(requireContext()))
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentFeedBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = PostPagingDataAdapter(
            onPostClick = { postId -> viewModel.obtainEvent(FeedEvent.PostClick(postId)) },
            onTierClick = { tier -> viewModel.obtainEvent(FeedEvent.TierClick(tier)) }
        )

        viewBinding.apply {
            swipeRefreshLayout.setOnRefreshListener {
                viewModel.obtainEvent(FeedEvent.Refresh)
            }
        }
        collectState()
    }

    private fun collectState() {
        lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                showLoading(state.isLoading)
                submitData(state.posts)
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        viewBinding.loadingErrorCompose.setContent { LoadingScreen(isLoading = isLoading) }
        viewBinding.loadingErrorCompose.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun submitData(posts: PagingData<PostWithStatsDomainModel>) {
        adapter?.submitData(viewLifecycleOwner.lifecycle, posts.map { it.toUiModel() })
        viewBinding.swipeRefreshLayout.isRefreshing = false
        viewBinding.loadingErrorCompose.visibility = View.GONE

        viewBinding.feedRv.adapter = adapter?.withLoadStateHeader(
            header = PostLoadStateAdapter(
                onRetryClick = { viewModel.obtainEvent(FeedEvent.Refresh) }
            )
        )
    }

//    private fun showError(isError: Boolean) {
//        viewBinding.loadingErrorCompose.setContent {
//            ErrorScreen(onRetryClick = { viewModel.obtainEvent(FeedEvent.Refresh) })
//        }
//        viewBinding.loadingErrorCompose.visibility = if (isError) View.VISIBLE else View.GONE
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }
}
