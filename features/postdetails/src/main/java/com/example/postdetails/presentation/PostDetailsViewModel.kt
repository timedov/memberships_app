package com.example.postdetails.presentation

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.common.utils.ExceptionHandlerDelegate
import com.example.common.utils.runSuspendCatching
import com.example.domain.usecase.GetPostByIdUseCase
import com.example.domain.usecase.GetUserDetailsUseCase
import com.example.postdetails.navigation.PostDetailsRouter
import com.example.postdetails.presentation.model.PostDetailsAction
import com.example.postdetails.presentation.model.PostDetailsEvent
import com.example.postdetails.presentation.model.PostDetailsState
import com.example.postdetails.usecase.GetCommentsUseCase
import com.example.postdetails.usecase.GetPostStatsByIdUseCase
import com.example.postdetails.usecase.IsPostFavoriteUseCase
import com.example.postdetails.usecase.SendCommentUseCase
import com.example.postdetails.usecase.SetPostFavoriteUseCase
import com.example.ui.base.BaseViewModel
import com.example.ui.utils.toUiModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class PostDetailsViewModel @Inject constructor(
    private val postDetailsRouter: PostDetailsRouter,
    private val getPostByIdUseCase: GetPostByIdUseCase,
    private val getUserDetailsUseCase: GetUserDetailsUseCase,
    private val getPostStatsByIdUseCase: GetPostStatsByIdUseCase,
    private val isPostFavoriteUseCase: IsPostFavoriteUseCase,
    private val getCommentsUseCase: GetCommentsUseCase,
    private val setPostFavoriteUseCase: SetPostFavoriteUseCase,
    private val sendCommentUseCase: SendCommentUseCase,
    private val exceptionHandlerDelegate: ExceptionHandlerDelegate
) : BaseViewModel<PostDetailsState, PostDetailsEvent, PostDetailsAction>(
    initialState = PostDetailsState()
) {

    init {
        loadPostDetails()
    }

    override fun obtainEvent(event: PostDetailsEvent) {
        when (event) {
            is PostDetailsEvent.Initiate -> _uiState.value =
                _uiState.value.copy(postId = event.postId, authorName = event.authorName)
            is PostDetailsEvent.BackClick -> popBackStack()
            is PostDetailsEvent.SubscribeClick -> navigateToSubscribeScreen()
            is PostDetailsEvent.FavoriteClick -> onFavoriteClicked()
            is PostDetailsEvent.CommentValueChanged -> onCommentValueChanged(event.value)
            is PostDetailsEvent.ProfileClick -> navigateToProfileScreen()
            is PostDetailsEvent.SendComment -> sendComment()
            is PostDetailsEvent.Refresh -> loadPostDetails()
        }
    }

    private fun loadPostDetails() {
        _uiState.value = _uiState.value.copy(isLoading = true, isError = false)

        viewModelScope.launch {
            runSuspendCatching(exceptionHandlerDelegate) {
                _uiState.value = _uiState.value.copy(
                    post = getPostByIdUseCase.invoke(_uiState.value.postId).toUiModel(),
                    userDetails = getUserDetailsUseCase
                        .invoke(username = _uiState.value.authorName)
                        .toUiModel(),
                    isFavorite = isPostFavoriteUseCase.invoke(postId = _uiState.value.postId),
                    commentsFlow = getCommentsUseCase
                        .invoke(_uiState.value.postId)
                        .cachedIn(viewModelScope),
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

    private fun loadPostStats() {
        viewModelScope.launch {
            runSuspendCatching(exceptionHandlerDelegate) {
                getPostStatsByIdUseCase.invoke(_uiState.value.postId)
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

    private fun navigateToSubscribeScreen() {
        postDetailsRouter.navigateToSubscribe(_uiState.value.authorName)
    }

    private fun onFavoriteClicked() {
        viewModelScope.launch {
            runSuspendCatching(exceptionHandlerDelegate) {
                setPostFavoriteUseCase.invoke(
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
        _uiState.value = _uiState.value.copy(commentValue = value)
    }

    private fun sendComment() {
        viewModelScope.launch {
            runSuspendCatching(exceptionHandlerDelegate) {
                sendCommentUseCase.invoke(
                    postId = _uiState.value.postId,
                    comment = _uiState.value.commentValue
                )
            }.onSuccess {
                _actionsFlow.emit(PostDetailsAction.CommentSent)
                onCommentValueChanged(value = "")

                loadPostStats()
            }.onFailure {
                _actionsFlow.emit(PostDetailsAction.CommentSendingFailed)
            }
        }
    }

    private fun navigateToProfileScreen() {
        postDetailsRouter.navigateToProfile(username = _uiState.value.authorName)
    }

    private fun popBackStack() {
        postDetailsRouter.popBackStack()
    }
}
