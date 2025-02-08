package com.example.feed.impl.presentation.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.ui.model.PostUiModel
import com.example.ui.view.composable.ErrorScreen

internal fun LazyListScope.postsList(
    posts: LazyPagingItems<PostUiModel>,
    onPostClick: (String) -> Unit,
    onProfileClick: (String) -> Unit,
) {
    posts.apply {
        when (loadState.refresh) {
            is LoadState.Error -> item {
                ErrorScreen(onRetryClick = { posts.retry() })
            }
            is LoadState.Loading ->
                item {
                    CircularProgressIndicator(modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth())
                }
            is LoadState.NotLoading -> {
                items(posts.itemCount) { index ->
                    posts[index]?.let {
                        PostItem(
                            post = it,
                            onPostClick = onPostClick,
                            onProfileClick = onProfileClick,
                            modifier = Modifier.padding(vertical = 4.dp)
                        )
                    }
                }
            }
        }
    }
}