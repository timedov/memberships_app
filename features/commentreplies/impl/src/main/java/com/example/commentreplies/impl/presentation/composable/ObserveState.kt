package com.example.commentreplies.impl.presentation.composable

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.example.commentreplies.impl.presentation.model.CommentRepliesState
import com.example.ui.model.CommentUiModel
import com.example.ui.view.composable.ErrorScreen
import com.example.ui.view.composable.LoadingScreen

@Composable
internal fun ObserveState(
    uiState: CommentRepliesState,
    comments: LazyPagingItems<CommentUiModel>,
    onCommentValueChange: (String) -> Unit,
    onRetryClick: () -> Unit,
    onProfileClick: (String) -> Unit,
    onSendComment: () -> Unit
) {

    if (uiState.isError) {
        ErrorScreen(onRetryClick)
    } else {
        CommentRepliesContent(
            parentComment = uiState.parentComment,
            comments = comments,
            commentValue = uiState.commentValue,
            onCommentValueChange = onCommentValueChange,
            onSendComment = onSendComment,
            onProfileClick = onProfileClick,
            modifier = Modifier.fillMaxSize().padding(horizontal = 8.dp)
        )

        LoadingScreen(isLoading = uiState.isLoading)
    }
}
