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

    private var postId: Long = -1L
    private var authorName: String = ""

    init {
        loadPostData()
    }

    override fun obtainEvent(event: PostDetailsEvent) {
        when (event) {
            is PostDetailsEvent.Initiate -> {
                postId = event.postId
                authorName = event.authorName
            }
            is PostDetailsEvent.BackClick -> popBackStack()
            is PostDetailsEvent.FavoriteClick -> onFavoriteClicked()
            is PostDetailsEvent.CommentValueChanged -> onCommentValueChanged(event.value)
            is PostDetailsEvent.ProfileClick -> navigateToProfileScreen()
            is PostDetailsEvent.SendComment -> sendComment()
            is PostDetailsEvent.Refresh -> loadPostData()
        }
    }

    private fun loadPostData() {
        _uiState.value = _uiState.value.copy(isLoading = true, isError = false)

        viewModelScope.launch {
            runSuspendCatching(exceptionHandlerDelegate) {
                getPostByIdUseCase.invoke(postId)
            }.onSuccess {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    isRefreshing = false,
                    post = it.toUiModel()
                )

                loadAuthorDetails()
            }.onFailure {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    isRefreshing = false,
                    isError = true
                )
            }
        }
    }

    private fun loadAuthorDetails() {
        viewModelScope.launch {
            runSuspendCatching(exceptionHandlerDelegate) {
                getUserDetailsUseCase.invoke(username = authorName)
            }.onSuccess {
                _uiState.value = _uiState.value.copy(
                    userDetails = it.toUiModel()
                )

                loadPostStats()
            }.onFailure {
                _uiState.value = _uiState.value.copy(isError = true)
            }
        }
    }

    private fun loadPostStats() {
        viewModelScope.launch {
            runSuspendCatching(exceptionHandlerDelegate) {
                getPostStatsByIdUseCase.invoke(postId)
            }.onSuccess {
                _uiState.value = _uiState.value.copy(postStats = it.toUiModel())

                isPostFavorite()
            }.onFailure {
                _uiState.value = _uiState.value.copy(isError = true)
            }
        }
    }

    private fun isPostFavorite() {
        viewModelScope.launch {
            runSuspendCatching(exceptionHandlerDelegate) {
                isPostFavoriteUseCase.invoke(postId = postId)
            }.onSuccess {
                _uiState.value = _uiState.value.copy(isFavorite = it)

                loadComments()
            }.onFailure {
                _uiState.value = _uiState.value.copy(isError = true)
            }
        }
    }

    private fun loadComments() {
        _uiState.value = _uiState.value.copy(
            commentsFlow = getCommentsUseCase.invoke(postId).cachedIn(viewModelScope)
        )
    }

    private fun onFavoriteClicked() {
        viewModelScope.launch {
            runSuspendCatching(exceptionHandlerDelegate) {
                setPostFavoriteUseCase.invoke(
                    postId = postId,
                    isFavorite = _uiState.value.isFavorite
                )
            }.onSuccess {
                _uiState.value = _uiState.value.copy(isFavorite = _uiState.value.isFavorite.not())
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
                sendCommentUseCase.invoke(postId = postId, comment = _uiState.value.commentValue)
            }.onSuccess {
                _actionsFlow.emit(PostDetailsAction.CommentSent)
                onCommentValueChanged(value = "")
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
