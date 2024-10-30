package com.example.feed.presentation

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.common.utils.ExceptionHandlerDelegate
import com.example.domain.usecase.GetPostsUseCase
import com.example.feed.navigation.FeedRouter
import com.example.feed.presentation.model.FeedAction
import com.example.feed.presentation.model.FeedEvent
import com.example.feed.presentation.model.FeedState
import com.example.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class FeedViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase,
    private val router: FeedRouter,
    private val exceptionHandlerDelegate: ExceptionHandlerDelegate
) : BaseViewModel<FeedState, FeedEvent, FeedAction>(
    initialState = FeedState()
) {

    init {
        loadPostsByTier()
    }

    private fun loadPostsByTier() {
        _uiState.value = _uiState.value.copy(
            isLoading = true,
            isRefreshing = false,
        )
        viewModelScope.launch {
            getPostsUseCase.invoke(tier = _uiState.value.selectedTier)
                .cachedIn(viewModelScope)
                .collect { pagingData ->
                    _uiState.value = _uiState.value.copy(
                        posts = pagingData,
                        isLoading = false
                    )
                }
        }
    }

    override fun obtainEvent(event: FeedEvent) {
        when (event) {
            is FeedEvent.TierClick -> {
                _uiState.value = _uiState.value.copy(
                    selectedTier = event.tier
                )
                loadPostsByTier()
            }
            is FeedEvent.Refresh -> {
                _uiState.value = _uiState.value.copy(
                    isRefreshing = true
                )
                loadPostsByTier()
            }
            is FeedEvent.PostClick -> router.navigateToDetailsScreen(event.id)
            is FeedEvent.ProfileClick -> router.navigateToProfile()
        }
    }
}
