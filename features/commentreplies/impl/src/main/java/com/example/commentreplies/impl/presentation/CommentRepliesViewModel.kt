package com.example.commentreplies.impl.presentation

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.postdetails.api.domain.usecase.GetCommentByIdUseCase
import com.example.postdetails.api.domain.usecase.GetCommentRepliesUseCase
import com.example.postdetails.api.domain.usecase.SendCommentReplyUseCase
import com.example.commentreplies.api.navigation.CommentRepliesRouter
import com.example.postdetails.api.presentation.utils.toUiModel
import com.example.common.utils.AppExceptionHandler
import com.example.common.utils.runSuspendCatching
import com.example.commentreplies.impl.presentation.model.CommentRepliesAction
import com.example.commentreplies.impl.presentation.model.CommentRepliesEvent
import com.example.commentreplies.impl.presentation.model.CommentRepliesState
import com.example.ui.base.BaseViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class CommentRepliesViewModel @Inject constructor(
    private val commentRepliesRouter: CommentRepliesRouter,
    private val getCommentByIdUseCase: GetCommentByIdUseCase,
    private val getCommentRepliesUseCase: GetCommentRepliesUseCase,
    private val sendCommentReplyUseCase: SendCommentReplyUseCase,
    private val appExceptionHandler: AppExceptionHandler,
) : BaseViewModel<CommentRepliesState, CommentRepliesEvent, CommentRepliesAction>(
    initialState = CommentRepliesState()
) {

    override fun obtainEvent(event: CommentRepliesEvent) {
        when (event) {
            is CommentRepliesEvent.Initiate -> {
                _uiState.value = _uiState.value.copy(
                    parentCommentId = event.commentId
                )
                loadParentComment()
                loadCommentReplies()
            }
            is CommentRepliesEvent.BackClick -> popBackStack()
            is CommentRepliesEvent.ProfileClick -> navigateToProfileScreen(event.username)
            is CommentRepliesEvent.CommentValueChanged -> onCommentValueChanged(event.value)
            is CommentRepliesEvent.SendComment -> sendComment()
            is CommentRepliesEvent.Refresh -> {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    isRefreshing = true,
                    isError = false
                )
                loadParentComment()
            }
        }
    }

    private fun loadParentComment() {
        _uiState.value = _uiState.value.copy(
            isLoading = true,
            isRefreshing = false,
            isError = false
        )
        viewModelScope.launch {
            runSuspendCatching(appExceptionHandler) {
                getCommentByIdUseCase.invoke(_uiState.value.parentCommentId)
            }.onSuccess {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    parentComment = it.toUiModel(),
                    isError = false
                )
            }.onFailure {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    isRefreshing = false,
                    isError = true
                )
            }
        }
    }

    private fun loadCommentReplies() {
        _uiState.value = _uiState.value.copy(
            commentsFlow = getCommentRepliesUseCase.invoke(_uiState.value.parentCommentId)
                .map { pagingData ->
                    pagingData.map { comment -> comment.toUiModel() }
                }.cachedIn(viewModelScope)
        )
    }

    private fun sendComment() {
        viewModelScope.launch {
            runSuspendCatching(appExceptionHandler) {
                sendCommentReplyUseCase.invoke(
                    parentCommentId = _uiState.value.parentCommentId,
                    comment = _uiState.value.commentValue.trim()
                )
            }.onSuccess {
                _actionsFlow.emit(CommentRepliesAction.CommentSent)

                onCommentValueChanged(value = "")
            }.onFailure {
                _actionsFlow.emit(CommentRepliesAction.CommentSendingFailed)
            }
        }
    }

    private fun onCommentValueChanged(value: String) {
        _uiState.value = _uiState.value.copy(commentValue = value.trimStart())
    }

    private fun navigateToProfileScreen(username: String) {
        commentRepliesRouter.navigateToProfile(username)
    }

    private fun popBackStack() {
        commentRepliesRouter.popBackStack()
    }
}
