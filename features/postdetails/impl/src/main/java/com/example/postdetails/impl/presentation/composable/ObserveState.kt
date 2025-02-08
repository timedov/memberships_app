package com.example.postdetails.impl.presentation.composable

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.example.postdetails.impl.presentation.model.PostDetailsState
import com.example.ui.model.CommentUiModel
import com.example.ui.view.composable.ErrorScreen
import com.example.ui.view.composable.LoadingScreen

@Composable
internal fun ObserveState(
    uiState: PostDetailsState,
    comments: LazyPagingItems<CommentUiModel>,
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
            comments = comments,
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
