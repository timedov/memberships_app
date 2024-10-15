package com.example.commentreplies.presentation.composables

import androidx.compose.runtime.Composable
import com.example.commentreplies.presentation.model.CommentRepliesState

@Composable
fun ObserveState(
    uiState: CommentRepliesState,
    onCommentValueChange: (String) -> Unit,
    onRetryClick: () -> Unit,
    onProfileClick: (String) -> Unit,
    onSendComment: () -> Unit
) {


}
