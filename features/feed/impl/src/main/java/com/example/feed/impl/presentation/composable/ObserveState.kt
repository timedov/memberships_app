package com.example.feed.impl.presentation.composable

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.example.feed.impl.presentation.model.FeedState
import com.example.ui.model.PostUiModel
import com.example.ui.view.composable.ErrorScreen
import com.example.ui.view.composable.LoadingScreen

@Composable
internal fun ObserveState(
    uiState: FeedState,
    posts: LazyPagingItems<PostUiModel>,
    onRetryClick: () -> Unit,
    onPostClick: (String) -> Unit,
    onProfileClick: (String) -> Unit
) {

    if (uiState.isError) {
        ErrorScreen(onRetryClick = onRetryClick)
    } else {
        FeedContent(
            posts = posts,
            onPostClick = onPostClick,
            onProfileClick = onProfileClick,
            modifier = Modifier.fillMaxSize()
        )

        LoadingScreen(isLoading = uiState.isLoading)
    }
}