package com.example.postdetails.impl.presentation

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.common.utils.AppExceptionHandler
import com.example.common.utils.runSuspendCatching
import com.example.feed.api.presentation.utils.toUiModel
import com.example.postdetails.api.navigation.PostDetailsRouter
import com.example.postdetails.api.presentation.utils.toUiModel
import com.example.postdetails.impl.domain.usecase.PostDetailsInteractor
import com.example.postdetails.impl.presentation.model.PostDetailsAction
import com.example.postdetails.impl.presentation.model.PostDetailsEvent
import com.example.postdetails.impl.presentation.model.PostDetailsState
import com.example.profile.api.presentation.utils.toUiModel
import com.example.ui.base.BaseViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class PostDetailsViewModel @Inject constructor(
    private val postDetailsRouter: PostDetailsRouter,
    private val postDetailsInteractor: PostDetailsInteractor,
    private val appExceptionHandler: AppExceptionHandler
) : BaseViewModel<PostDetailsState, PostDetailsEvent, PostDetailsAction>(
    initialState = PostDetailsState()
) {
    override fun obtainEvent(event: PostDetailsEvent) {
        when (event) {
            is PostDetailsEvent.Initiate -> {
                _uiState.value = _uiState.value.copy(postId = event.postId)
                loadPostData()
            }
            is PostDetailsEvent.BackClick -> popBackStack()
            is PostDetailsEvent.SubscribeClick -> navigateToProfileScreen()
            is PostDetailsEvent.FavoriteClick -> onFavoriteClicked()
            is PostDetailsEvent.CommentValueChanged -> onCommentValueChanged(event.value)
            is PostDetailsEvent.ProfileClick -> navigateToProfileScreen()
            is PostDetailsEvent.ReplyClick -> navigateToCommentRepliesScreen(event.commentId)
            is PostDetailsEvent.SendComment -> sendComment()
            is PostDetailsEvent.Refresh -> loadPostData()
        }
    }

    private fun loadPostData() {
        viewModelScope.launch {
            runSuspendCatching(appExceptionHandler) {
                postDetailsInteractor.getPostById(_uiState.value.postId)
            }.onSuccess {
                _uiState.value = _uiState.value.copy(
                    post = it.toUiModel(),
                )

                loadPostDetails()
                loadComments()
            }.onFailure {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    isRefreshing = false,
                    isError = true
                )
            }
        }
    }

    private fun loadPostDetails() {
        _uiState.value = _uiState.value.copy(isLoading = true, isError = false)

        viewModelScope.launch {
            runSuspendCatching(appExceptionHandler) {
                _uiState.value = _uiState.value.copy(
                    userDetails = postDetailsInteractor
                        .getUserDetails(username = _uiState.value.post.author)
                        .toUiModel(),
                    requiresSubscription = isSubscriptionRequired(),
                    isFavorite = postDetailsInteractor.isPostFavorite(postId = _uiState.value.postId),
                    isLoading = false,
                    isRefreshing = false
                )

                loadPostStats()
            }.onFailure {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    isRefreshing = false,
                    isError = true
                )
            }
        }
    }

    private fun loadComments() {
        _uiState.value = _uiState.value
            .copy(
                commentsFlow = postDetailsInteractor.getComments(_uiState.value.postId)
                    .map { pagingData ->
                        pagingData.map { it.toUiModel() }
                    }.cachedIn(viewModelScope),
            )
    }

    private fun loadPostStats() {
        viewModelScope.launch {
            runSuspendCatching(appExceptionHandler) {
                postDetailsInteractor.getPostStatsById(_uiState.value.postId)
            }.onSuccess {
                _uiState.value = _uiState.value.copy(
                    postStats = it.toUiModel(),
                )
            }.onFailure {
                _uiState.value = _uiState.value.copy(
                    isError = true
                )
            }
        }
    }

    private suspend fun isSubscriptionRequired(): Boolean =
        _uiState.value.post.requiresSubscription &&
                postDetailsInteractor.isCurrentUserSubscribed(
                    subscribedTo = _uiState.value.post.author
                ).not()

    private fun onFavoriteClicked() {
        viewModelScope.launch {
            runSuspendCatching(appExceptionHandler) {
                postDetailsInteractor.setPostFavorite(
                    postId = _uiState.value.postId,
                    isFavorite = _uiState.value.isFavorite.not()
                )
            }.onSuccess {
                _uiState.value = _uiState.value.copy(isFavorite = _uiState.value.isFavorite.not())

                loadPostStats()
            }.onFailure {
                _actionsFlow.emit(PostDetailsAction.SetFavoriteFailed)
            }
        }
    }

    private fun onCommentValueChanged(value: String) {
        _uiState.value = _uiState.value.copy(commentValue = value.trimStart())
    }

    private fun sendComment() {
        viewModelScope.launch {
            runSuspendCatching(appExceptionHandler) {
                postDetailsInteractor.sendComment(
                    postId = _uiState.value.postId,
                    comment = _uiState.value.commentValue.trim()
                )
            }.onSuccess {
                _actionsFlow.emit(PostDetailsAction.CommentSent)
                onCommentValueChanged(value = "")

                loadPostStats()
                loadComments()
            }.onFailure {
                _actionsFlow.emit(PostDetailsAction.CommentSendingFailed)
            }
        }
    }

    private fun navigateToProfileScreen() {
        postDetailsRouter.navigateToProfile(username = _uiState.value.userDetails.username)
    }

    private fun navigateToCommentRepliesScreen(commentId: String) {
        postDetailsRouter.navigateToCommentReplies(commentId = commentId)
    }

    private fun popBackStack() {
        postDetailsRouter.popBackStack()
    }
}
