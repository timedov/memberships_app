package com.example.commentreplies.presentation.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.domain.model.CommentDomainModel
import com.example.ui.model.CommentUiModel
import com.example.ui.view.composables.CommentItem
import com.example.ui.view.composables.CommentTextField
import com.example.ui.view.composables.commentsList
import kotlinx.coroutines.flow.Flow

@Composable
fun CommentRepliesContent(
    parentComment: CommentUiModel,
    commentsFlow: Flow<PagingData<CommentDomainModel>>,
    commentValue: String,
    onCommentValueChange: (String) -> Unit,
    onSendComment: () -> Unit,
    onProfileClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val comments = commentsFlow.collectAsLazyPagingItems()

    Column(
        modifier = modifier,
    ) {
        CommentItem(
            comment = parentComment,
            onProfileClick = onProfileClick,
            onReplyClick = {},
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
