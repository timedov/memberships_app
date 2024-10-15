package com.example.commentreplies.presentation

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.commentreplies.navigation.CommentRepliesRouter
import com.example.commentreplies.presentation.model.CommentRepliesAction
import com.example.commentreplies.presentation.model.CommentRepliesEvent
import com.example.commentreplies.presentation.model.CommentRepliesState
import com.example.commentreplies.usecase.GetCommentByIdUseCase
import com.example.commentreplies.usecase.GetCommentRepliesUseCase
import com.example.commentreplies.usecase.SendCommentReplyUseCase
import com.example.common.utils.ExceptionHandlerDelegate
import com.example.common.utils.runSuspendCatching
import com.example.ui.base.BaseViewModel
import com.example.ui.utils.toUiModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class CommentRepliesViewModel @Inject constructor(
    private val commentRepliesRouter: CommentRepliesRouter,
    private val getCommentByIdUseCase: GetCommentByIdUseCase,
    private val getCommentRepliesUseCase: GetCommentRepliesUseCase,
    private val sendCommentReplyUseCase: SendCommentReplyUseCase,
    private val exceptionHandlerDelegate: ExceptionHandlerDelegate,
) : BaseViewModel<CommentRepliesState, CommentRepliesEvent, CommentRepliesAction>(
    initialState = CommentRepliesState()
) {

    init {
        loadParentComment()
    }

    override fun obtainEvent(event: CommentRepliesEvent) {
        when (event) {
            is CommentRepliesEvent.Initiate ->
                _uiState.value = _uiState.value.copy(
                    parentCommentId = event.commentId
                )
            is CommentRepliesEvent.BackClick -> popBackStack()
            is CommentRepliesEvent.ProfileClick -> navigateToProfileScreen(event.username)
            is CommentRepliesEvent.CommentValueChanged -> onCommentValueChanged(event.value)
            is CommentRepliesEvent.SendComment -> sendComment()
            is CommentRepliesEvent.Refresh -> loadParentComment()
        }
    }

    private fun loadParentComment() {
        _uiState.value = _uiState.value.copy(
            isLoading = false,
            isRefreshing = false,
            isError = false
        )
        viewModelScope.launch {
            runSuspendCatching(exceptionHandlerDelegate) {
                getCommentByIdUseCase.invoke(_uiState.value.parentCommentId)
            }.onSuccess {
                _uiState.value = _uiState.value.copy(
                    parentComment = it.toUiModel()
                )

                loadCommentReplies()
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
        viewModelScope.launch {
            runSuspendCatching(exceptionHandlerDelegate) {
                getCommentRepliesUseCase.invoke(parentCommentId = _uiState.value.parentCommentId)
            }.onSuccess {
                _uiState.value = _uiState.value.copy(
                    commentsFlow = it.cachedIn(viewModelScope)
                )
            }.onFailure {
                _uiState.value = _uiState.value.copy(isError = true)
            }
        }
    }

    private fun sendComment() {
        viewModelScope.launch {
            runSuspendCatching(exceptionHandlerDelegate) {
                sendCommentReplyUseCase.invoke(
                    parentCommentId = _uiState.value.parentCommentId,
                    comment = _uiState.value.commentValue
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
        _uiState.value = _uiState.value.copy(commentValue = value)
    }

    private fun navigateToProfileScreen(username: String) {
        commentRepliesRouter.navigateToProfile(username)
    }

    private fun popBackStack() {
        commentRepliesRouter.popBackStack()
    }
}
