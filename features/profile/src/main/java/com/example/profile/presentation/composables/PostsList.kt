package com.example.profile.presentation.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.domain.model.PostDomainModel
import com.example.profile.R
import com.example.ui.themes.OnSurfaceTextAlpha
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
        if (posts.itemCount == 0) {
            item {
                Text(
                    text = stringResource(R.string.no_posts),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = OnSurfaceTextAlpha),
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        }

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