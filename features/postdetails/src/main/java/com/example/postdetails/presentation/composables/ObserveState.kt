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
    state: PostDetailsState,
    onRefreshingStateChange: (Boolean) -> Unit,
    onRetryClick: () -> Unit,
    onFavoriteClick: () -> Unit,
    onProfileClick: (String) -> Unit,
    onSendComment: (String) -> Unit
) {

    when (state) {
        is PostDetailsState.Loading -> LoadingScreen(isLoading = true)
        is PostDetailsState.Content -> {
            onRefreshingStateChange(false)

            PostDetailsContent(
                userDetails = state.userDetails,
                post = state.post,
                postStats = state.postStats,
                isFavorite = state.isFavorite,
                commentsResponse = state.commentsResponse,
                onFavoriteClick = onFavoriteClick,
                onProfileClick = onProfileClick,
                onSendComment = onSendComment,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            )
        }
        is PostDetailsState.Error -> ErrorScreen(onRetryClick = onRetryClick)
    }
}
