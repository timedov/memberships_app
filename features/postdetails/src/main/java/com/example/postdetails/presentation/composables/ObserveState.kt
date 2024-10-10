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
    onFavoriteClick: () -> Unit,
    onProfileClick: (String) -> Unit,
    onSendComment: () -> Unit
) {

    if (uiState.isError) {
        ErrorScreen(onRetryClick = onRetryClick)
    } else {
        PostDetailsContent(
            userDetails = uiState.userDetails,
            post = uiState.post,
            postStats = uiState.postStats,
            isFavorite = uiState.isFavorite,
            commentsFlow = uiState.commentsFlow,
            commentValue = uiState.commentValue,
            onCommentValueChange = onCommentValueChange,
            onFavoriteClick = onFavoriteClick,
            onProfileClick = onProfileClick,
            onSendComment = onSendComment,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        )

        LoadingScreen(isLoading = uiState.isLoading)
    }
}
