package com.example.postdetails.presentation.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.postdetails.presentation.model.PostDetailsState
import com.example.ui.view.composables.ErrorScreen
import com.example.ui.view.composables.LoadingScreen

@Composable
fun ObserveState(
    uiState: PostDetailsState,
    onCommentValueChange: (String) -> Unit,
    onRetryClick: () -> Unit,
    onSubscribeClick: () -> Unit,
    onSubscribeDismiss: () -> Unit,
    onFavoriteClick: () -> Unit,
    onProfileClick: (String) -> Unit,
    onReplyClick: (String) -> Unit,
    onSendComment: () -> Unit
) {

    if (uiState.isError) {
        ErrorScreen(onRetryClick = onRetryClick)
    } else {
        PostDetailsContent(
            userDetails = uiState.userDetails,
            post = uiState.post,
            postStats = uiState.postStats,
            requiresSubscription = uiState.requiresSubscription,
            isFavorite = uiState.isFavorite,
            player = uiState.player,
            commentsFlow = uiState.commentsFlow,
            commentValue = uiState.commentValue,
            onSubscribeClick = onSubscribeClick,
            onSubscribeDismiss = onSubscribeDismiss,
            onCommentValueChange = onCommentValueChange,
            onFavoriteClick = onFavoriteClick,
            onProfileClick = onProfileClick,
            onReplyClick = onReplyClick,
            onSendComment = onSendComment,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        )

        LoadingScreen(isLoading = uiState.isLoading)
    }
}
