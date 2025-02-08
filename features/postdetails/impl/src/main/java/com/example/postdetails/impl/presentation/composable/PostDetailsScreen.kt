package com.example.postdetails.impl.presentation.composable

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.postdetails.impl.R
import com.example.postdetails.impl.presentation.PostDetailsViewModel
import com.example.postdetails.impl.presentation.model.PostDetailsAction
import com.example.postdetails.impl.presentation.model.PostDetailsEvent
import com.example.ui.view.composable.CenterAlignedTopAppBarWithBackButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PostDetailsScreen(viewModel: PostDetailsViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val action by viewModel.actionsFlow.collectAsStateWithLifecycle(
        initialValue = PostDetailsAction.Initiate
    )

    val comments = uiState.commentsFlow.collectAsLazyPagingItems()

    ObserveActions(action = action)

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
            isRefreshing = uiState.isRefreshing,
            onRefresh = { viewModel.obtainEvent(PostDetailsEvent.Refresh) }
        ) {
            ObserveState(
                uiState = uiState,
                comments = comments,
                onCommentValueChange =
                    { viewModel.obtainEvent(PostDetailsEvent.CommentValueChanged(it)) },
                onSubscribeClick = { viewModel.obtainEvent(PostDetailsEvent.SubscribeClick) },
                onSubscribeDismiss = { viewModel.obtainEvent(PostDetailsEvent.BackClick) },
                onRetryClick = { viewModel.obtainEvent(PostDetailsEvent.Refresh) },
                onFavoriteClick = { viewModel.obtainEvent(PostDetailsEvent.FavoriteClick) },
                onProfileClick = { viewModel.obtainEvent(PostDetailsEvent.ProfileClick(it)) },
                onReplyClick = { viewModel.obtainEvent(PostDetailsEvent.ReplyClick(it)) },
                onSendComment = { viewModel.obtainEvent(PostDetailsEvent.SendComment) },
            )
        }
    }
}
