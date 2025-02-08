package com.example.ui.view.composable

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.ui.model.CommentUiModel

fun LazyListScope.commentsList(
    comments: LazyPagingItems<CommentUiModel>,
    replyButtonEnabled: Boolean = true,
    onReplyClick: (String) -> Unit = {},
    onProfileClick: (String) -> Unit,
) {
    comments.apply {
        when (loadState.refresh) {
            is LoadState.Error -> item {
                ErrorScreen(onRetryClick = { comments.retry() })
            }
            is LoadState.Loading ->
                item {
                    CircularProgressIndicator(modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth())
                }
            is LoadState.NotLoading -> {
                items(comments.itemCount) { index ->
                    comments[index]?.let {
                        CommentItem(
                            comment = it,
                            onProfileClick = onProfileClick,
                            onReplyClick = onReplyClick,
                            replyButtonEnabled = replyButtonEnabled,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }
                }
            }
        }
    }
}
