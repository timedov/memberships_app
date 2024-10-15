package com.example.commentreplies.presentation.composables

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
import com.example.commentreplies.R
import com.example.commentreplies.presentation.CommentRepliesViewModel
import com.example.commentreplies.presentation.model.CommentRepliesAction
import com.example.commentreplies.presentation.model.CommentRepliesEvent
import com.example.ui.view.composables.CenterAlignedTopAppBarWithBackButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentRepliesScreen(viewModel: CommentRepliesViewModel) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val action by viewModel.actionsFlow.collectAsStateWithLifecycle(
        initialValue = CommentRepliesAction.Initiate
    )

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
            onRefresh = { viewModel.obtainEvent(CommentRepliesEvent.Refresh) }
        ) {
            ObserveState(
                uiState = uiState,
                onCommentValueChange =
                    { viewModel.obtainEvent(CommentRepliesEvent.CommentValueChanged(it)) },
                onRetryClick = { viewModel.obtainEvent(CommentRepliesEvent.Refresh) },
                onProfileClick = { viewModel.obtainEvent(CommentRepliesEvent.ProfileClick(it)) },
                onSendComment = { viewModel.obtainEvent(CommentRepliesEvent.SendComment) },
            )
        }

    }
}
