package com.example.commentreplies.impl.presentation.composable

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
import com.example.commentreplies.impl.R
import com.example.commentreplies.impl.presentation.CommentRepliesViewModel
import com.example.commentreplies.impl.presentation.model.CommentRepliesAction
import com.example.commentreplies.impl.presentation.model.CommentRepliesEvent
import com.example.ui.view.composable.CenterAlignedTopAppBarWithBackButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CommentRepliesScreen(viewModel: CommentRepliesViewModel) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val action by viewModel.actionsFlow.collectAsStateWithLifecycle(
        initialValue = CommentRepliesAction.Initiate
    )

    val comments = uiState.commentsFlow.collectAsLazyPagingItems()

    ObserveActions(action)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBarWithBackButton(
                title = stringResource(R.string.comment_replies),
                onBackClick = { viewModel.obtainEvent(CommentRepliesEvent.BackClick) }
            )
        }
    ) { paddingValues ->
        PullToRefreshBox(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            isRefreshing = uiState.isRefreshing,
            onRefresh = {
                viewModel.obtainEvent(CommentRepliesEvent.Refresh)
                comments.refresh()
            }
        ) {
            ObserveState(
                uiState = uiState,
                comments = comments,
                onCommentValueChange =
                    { viewModel.obtainEvent(CommentRepliesEvent.CommentValueChanged(it)) },
                onRetryClick = { viewModel.obtainEvent(CommentRepliesEvent.Refresh) },
                onProfileClick = { viewModel.obtainEvent(CommentRepliesEvent.ProfileClick(it)) },
                onSendComment = {
                    viewModel.obtainEvent(CommentRepliesEvent.SendComment)
                },
            )
        }

    }
}
