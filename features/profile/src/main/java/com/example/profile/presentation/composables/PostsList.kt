package com.example.profile.presentation.composables

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.domain.model.PostDomainModel
import com.example.ui.utils.toUiModel
import com.example.ui.view.composables.ErrorScreen
import com.example.ui.view.composables.LoadingScreen

@Composable
fun PostsList(
    posts: LazyPagingItems<PostDomainModel>,
    onPostClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(posts.itemCount) { index ->
            posts[index]?.let { PostItem(post = it.toUiModel(), onClick = onPostClick) }
        }
    }

    posts.apply {
        when (loadState.refresh) {
            is LoadState.Error -> ErrorScreen(onRetryClick = { posts.retry() })
            LoadState.Loading -> LoadingScreen(isLoading = true)
            is LoadState.NotLoading -> ErrorScreen(onRetryClick = { posts.retry() })
        }
    }
}