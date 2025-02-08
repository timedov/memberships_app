package com.example.commentreplies.impl.presentation.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.ui.model.CommentUiModel
import com.example.ui.view.composable.CommentItem
import com.example.ui.view.composable.CommentTextField
import com.example.ui.view.composable.commentsList
import kotlinx.coroutines.flow.Flow

@Composable
internal fun CommentRepliesContent(
    parentComment: CommentUiModel,
    comments: LazyPagingItems<CommentUiModel>,
    commentValue: String,
    onCommentValueChange: (String) -> Unit,
    onSendComment: () -> Unit,
    onProfileClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
    ) {
        CommentItem(
            comment = parentComment,
            onProfileClick = onProfileClick,
            replyButtonEnabled = false,
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
        )

        LazyColumn(
            modifier = Modifier
                .padding(top = 12.dp, start = 24.dp)
                .weight(1f)
                .fillMaxWidth()
        ) {
            commentsList(
                comments = comments,
                replyButtonEnabled = false,
                onProfileClick = onProfileClick,
            )
        }

        CommentTextField(
            value = commentValue,
            onValueChange = onCommentValueChange,
            onCommentSend = onSendComment,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp)
        )
    }
}
