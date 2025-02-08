package com.example.feed.impl.presentation.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.ui.model.PostUiModel
import kotlinx.coroutines.flow.Flow

@Composable
internal fun FeedContent(
    posts: LazyPagingItems<PostUiModel>,
    onPostClick: (String) -> Unit,
    onProfileClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier,
    ) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .background(color = MaterialTheme.colorScheme.surfaceContainer)
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            postsList(
                posts = posts,
                onPostClick = onPostClick,
                onProfileClick = onProfileClick,
            )
        }
    }
}