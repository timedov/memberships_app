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
    initialState = PostDetailsState.Loading
) {

    var postId: Long = -1L
    var authorName: String = ""

    init {
        loadPostData()
    }

    override fun obtainEvent(event: PostDetailsEvent) {
        when (event) {
            is PostDetailsEvent.BackClick -> popBackStack()
            is PostDetailsEvent.FavoriteClick -> onFavoriteClicked(event.isFavorite)
            is PostDetailsEvent.ProfileClick -> navigateToProfileScreen()
            is PostDetailsEvent.SendComment -> sendComment(event.text)
            is PostDetailsEvent.Refresh -> loadPostData()
        }
    }

    private fun loadPostData() {
        _uiState.value = PostDetailsState.Loading

        viewModelScope.launch {
            runSuspendCatching(exceptionHandlerDelegate) {
                getPostByIdUseCase.invoke(postId)
            }.onSuccess {
                _uiState.value = PostDetailsState.Content(post = it.toUiModel())

                loadAuthorDetails()
            }.onFailure {
                _uiState.value = PostDetailsState.Error
            }
        }
    }

    private fun loadAuthorDetails() {
        viewModelScope.launch {
            runSuspendCatching(exceptionHandlerDelegate) {
                getUserDetailsUseCase.invoke(username = authorName)
            }.onSuccess {
                _uiState.value =
                    (_uiState.value as PostDetailsState.Content).copy(userDetails = it.toUiModel())

                loadPostStats()
            }.onFailure {
                _uiState.value = PostDetailsState.Error
            }
        }
    }

    private fun loadPostStats() {
        viewModelScope.launch {
            runSuspendCatching(exceptionHandlerDelegate) {
                getPostStatsByIdUseCase.invoke(postId)
            }.onSuccess {
                _uiState.value =
                    (_uiState.value as PostDetailsState.Content).copy(postStats = it.toUiModel())

                isPostFavorite()
            }.onFailure {
                _uiState.value = PostDetailsState.Error
            }
        }
    }

    private fun isPostFavorite() {
        viewModelScope.launch {
            runSuspendCatching(exceptionHandlerDelegate) {
                isPostFavoriteUseCase.invoke(postId = postId)
            }.onSuccess {
                _uiState.value =
                    (_uiState.value as PostDetailsState.Content).copy(isFavorite = it)

                loadComments()
            }.onFailure {
                _uiState.value = PostDetailsState.Error
            }
        }
    }

    private fun loadComments() {
        _uiState.value = (_uiState.value as PostDetailsState.Content).copy(
            commentsResponse = getCommentsUseCase.invoke(postId = postId).cachedIn(viewModelScope)
        )
    }

    private fun onFavoriteClicked(isFavorite: Boolean) {
        viewModelScope.launch {
            runSuspendCatching(exceptionHandlerDelegate) {
                setPostFavoriteUseCase.invoke(postId = postId, isFavorite = isFavorite)
            }.onSuccess {
                _uiState.value =
                    (_uiState.value as PostDetailsState.Content).copy(isFavorite = isFavorite)
            }.onFailure {
                _actionsFlow.emit(PostDetailsAction.SetFavoriteFailed)
            }
        }
    }

    private fun sendComment(text: String) {
        viewModelScope.launch {
            runSuspendCatching(exceptionHandlerDelegate) {
                sendCommentUseCase.invoke(postId = postId, comment = text)
            }.onSuccess {
                _actionsFlow.emit(PostDetailsAction.CommentSent)
            }.onFailure {
                _actionsFlow.emit(PostDetailsAction.CommentSendingFailed)
            }
        }
    }

    private fun navigateToProfileScreen() {
        postDetailsRouter.navigateToProfile(username = authorName)
    }

    private fun popBackStack() {
        postDetailsRouter.popBackStack()
    }
}
