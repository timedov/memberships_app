package com.example.feed.impl.presentation

import androidx.paging.map
import com.example.feed.api.domain.usecase.GetPostsUseCase
import com.example.feed.api.navigation.FeedRouter
import com.example.common.utils.AppExceptionHandler
import com.example.feed.impl.presentation.model.FeedAction
import com.example.feed.impl.presentation.model.FeedEvent
import com.example.feed.impl.presentation.model.FeedState
import com.example.feed.api.presentation.utils.toUiModel
import com.example.ui.base.BaseViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class FeedViewModel @Inject constructor(
    private val router: FeedRouter,
    private val getPostsUseCase: GetPostsUseCase
) : BaseViewModel<FeedState, FeedEvent, FeedAction>(
    initialState = FeedState()
) {
    init {
        loadPosts()
    }

    private fun loadPosts() {
        _uiState.value = _uiState.value.copy(
            isLoading = false,
            isRefreshing = false,
            postsFlow = getPostsUseCase.invoke().map { pagingData ->
                pagingData.map { it.toUiModel() }
            }
        )
    }

    override fun obtainEvent(event: FeedEvent) {
        when (event) {
            is FeedEvent.Refresh -> {
                _uiState.value = _uiState.value.copy(
                    isRefreshing = true,
                    isLoading = false,
                    isError = false
                )
                loadPosts()
            }
            is FeedEvent.PostClick -> router.navigateToDetailsScreen(event.id)
            is FeedEvent.ProfileClick -> router.navigateToProfile(event.username)
        }
    }
}
