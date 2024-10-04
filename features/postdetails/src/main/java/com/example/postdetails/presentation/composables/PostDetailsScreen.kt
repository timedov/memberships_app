package com.example.postdetails.presentation.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.postdetails.R
import com.example.postdetails.presentation.PostDetailsViewModel
import com.example.postdetails.presentation.model.PostDetailsEvent
import com.example.ui.view.composables.CenterAlignedTopAppBarWithBackButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostDetailsScreen(viewModel: PostDetailsViewModel) {
    val state by viewModel.uiState.collectAsState()

    var isRefreshing by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBarWithBackButton(
                title = stringResource(R.string.post_details),
                onBackClick = { viewModel.obtainEvent(PostDetailsEvent.BackClick) }
            )
        }
    ) { paddingValues ->
        PullToRefreshBox(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            isRefreshing = isRefreshing,
            onRefresh = {
                viewModel.obtainEvent(PostDetailsEvent.Refresh)
                isRefreshing = true
            }
        ) {
            ObserveState(
                state = state,
                onRefreshingStateChange = { isRefreshing = it },
                onRetryClick = { viewModel.obtainEvent(PostDetailsEvent.Refresh) },
                onFavoriteClick = { viewModel.obtainEvent(PostDetailsEvent.FavoriteClick) },
                onProfileClick = { viewModel.obtainEvent(PostDetailsEvent.ProfileClick(it)) },
                onSendComment = { viewModel.obtainEvent(PostDetailsEvent.SendComment(it)) },
            )
        }
    }
}
