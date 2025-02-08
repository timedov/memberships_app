package com.example.feed.impl.presentation.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.feed.impl.presentation.FeedViewModel
import com.example.feed.impl.presentation.model.FeedEvent
import com.example.feed.impl.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun FeedScreen(viewModel: FeedViewModel) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val posts = uiState.postsFlow.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text(text = stringResource(R.string.feed)) })
        }
    ) { paddingValues ->
        PullToRefreshBox(
            modifier = Modifier
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize(),
            isRefreshing = uiState.isRefreshing,
            onRefresh = {
                viewModel.obtainEvent(FeedEvent.Refresh)
                posts.refresh()
            }
        ) {
            ObserveState(
                uiState = uiState,
                posts = posts,
                onRetryClick = { viewModel.obtainEvent(FeedEvent.Refresh) },
                onPostClick = { viewModel.obtainEvent(FeedEvent.PostClick(it)) },
                onProfileClick = { viewModel.obtainEvent(FeedEvent.ProfileClick(it)) }
            )
        }
    }
}