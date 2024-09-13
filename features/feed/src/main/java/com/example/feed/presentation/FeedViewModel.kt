package com.example.feed.presentation

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.common.utils.ExceptionHandlerDelegate
import com.example.common.utils.runSuspendCatching
import com.example.domain.model.Tier
import com.example.domain.repository.PostRepository
import com.example.feed.navigation.FeedRouter
import com.example.feed.presentation.model.FeedAction
import com.example.feed.presentation.model.FeedEvent
import com.example.feed.presentation.model.FeedState
import com.example.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class FeedViewModel @Inject constructor(
    private val postRepository: PostRepository,
    private val router: FeedRouter,
    private val exceptionHandlerDelegate: ExceptionHandlerDelegate
) : BaseViewModel<FeedState, FeedEvent, FeedAction>(
    initialState = FeedState.Initial
) {

    init {
        loadPosts()
    }

    private fun loadPosts() {
        _uiState.value = FeedState.Loading
        viewModelScope.launch {
            runSuspendCatching(exceptionHandlerDelegate) {
                postRepository.getPosts()
            }.onSuccess {
                it.cachedIn(viewModelScope)
                    .collect { pagingData ->
                        _uiState.value = FeedState.Content(pagingData, Tier.ALL_TIERS)
                    }
            }.onFailure {
                _uiState.value = FeedState.Error(error = it)
            }
        }
    }

    override fun obtainEvent(event: FeedEvent) {
        when (event) {
            is FeedEvent.TierClick -> selectTier(event.tier)
            is FeedEvent.PostClick -> router.navigateToDetailsScreen(event.id)
            FeedEvent.Refresh -> refreshPosts()
            FeedEvent.Retry -> retryLoadPosts()
        }
    }

    private fun selectTier(tier: Tier) {
        _uiState.value = FeedState.Loading
        viewModelScope.launch {
            runSuspendCatching(exceptionHandlerDelegate) {
                postRepository.getPostsByTier(tier)
            }.onSuccess {
                it.cachedIn(viewModelScope)
                .collect { pagingData ->
                    _uiState.value = FeedState.Content(pagingData, tier)
                }
            }.onFailure {
                _uiState.value = FeedState.Error(error = it)
            }
        }
    }

    private fun refreshPosts() {
        loadPosts()
    }

    private fun retryLoadPosts() {
        loadPosts()
    }
}
