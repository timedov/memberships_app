package com.example.commentreplies.presentation.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.commentreplies.presentation.model.CommentRepliesState
import com.example.ui.view.composables.ErrorScreen
import com.example.ui.view.composables.LoadingScreen

@Composable
fun ObserveState(
    uiState: CommentRepliesState,
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
            commentsFlow = uiState.commentsFlow,
            commentValue = uiState.commentValue,
            onCommentValueChange = onCommentValueChange,
            onSendComment = onSendComment,
            onProfileClick = onProfileClick,
            modifier = Modifier.fillMaxSize().padding(horizontal = 8.dp)
        )

        LoadingScreen(isLoading = uiState.isLoading)
    }
}
