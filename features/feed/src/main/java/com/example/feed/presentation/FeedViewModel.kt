package com.example.feed.presentation

import android.util.Log
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
    initialState = FeedState.Loading
) {

    private var selectedTier: Tier = Tier.ALL_TIERS

    init {
        loadPostsByTier()
    }

    private fun loadPostsByTier() {
        viewModelScope.launch {
            runSuspendCatching(exceptionHandlerDelegate) {
                postRepository.getPostsByTier(selectedTier)
            }.onSuccess {
                it.cachedIn(viewModelScope)
                    .collect { pagingData ->
                        _uiState.value =
                            FeedState.Content(posts = pagingData, selectedTier = selectedTier)
                    }
            }.onFailure {
                Log.e("FeedViewModel", "An error occurred while requesting data", it)
                _uiState.value = FeedState.Error(it)
            }
        }
    }

    override fun obtainEvent(event: FeedEvent) {
        when (event) {
            is FeedEvent.TierClick -> {
                selectedTier = event.tier
                _uiState.value = FeedState.Loading
                loadPostsByTier()
            }
            is FeedEvent.Refresh -> {
                _uiState.value = FeedState.Loading
                loadPostsByTier()
            }
            is FeedEvent.PostClick -> {
                navigateToDetailsScreen(event.id)
            }
            is FeedEvent.ProfileClick -> {
                navigateToProfileScreen()
            }
        }
    }

    private fun navigateToProfileScreen() {
        router.navigateToProfile()
    }

    private fun navigateToDetailsScreen(id: Long) {
        router.navigateToDetailsScreen(id)
    }
}
