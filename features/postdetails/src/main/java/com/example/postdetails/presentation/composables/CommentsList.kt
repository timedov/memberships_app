package com.example.postdetails.presentation.composables

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
import com.example.domain.model.CommentDomainModel
import com.example.ui.utils.toUiModel
import com.example.ui.view.composables.ErrorScreen

fun LazyListScope.commentsList(
    comments: LazyPagingItems<CommentDomainModel>,
    onProfileClick: (String) -> Unit,
) {
    comments.apply {
        when (loadState.refresh) {
            is LoadState.Error -> item {
                ErrorScreen(onRetryClick = { comments.retry() })
                Log.e("CommentsList", (loadState.refresh as LoadState.Error).error.message.orEmpty())
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
                            comment = it.toUiModel(),
                            onProfileClick = onProfileClick,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }
                }
            }
        }
    }
}