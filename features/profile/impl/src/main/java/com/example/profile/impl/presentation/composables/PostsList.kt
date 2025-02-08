package com.example.profile.impl.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.ui.model.PostUiModel
import com.example.ui.themes.Shapes
import com.example.ui.view.composable.ErrorScreen

internal fun LazyListScope.postsList(
    posts: LazyPagingItems<PostUiModel>,
    onPostClick: (String) -> Unit,
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
                            onItemClick = onPostClick,
                            modifier = Modifier
                                .padding(horizontal = 4.dp, vertical = 4.dp)
                                .background(
                                    color = MaterialTheme.colorScheme.surface,
                                    shape = Shapes.medium
                                )
                        )
                    }
                }
            }
        }
    }
}