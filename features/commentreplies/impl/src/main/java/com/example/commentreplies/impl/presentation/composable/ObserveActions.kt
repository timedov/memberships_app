package com.example.commentreplies.impl.presentation.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.commentreplies.impl.R
import com.example.commentreplies.impl.presentation.model.CommentRepliesAction
import com.example.ui.view.composable.ShowToast

@Composable
internal fun ObserveActions(action: CommentRepliesAction) {

    when (action) {
        CommentRepliesAction.Initiate -> Unit
        CommentRepliesAction.CommentSent ->
            ShowToast(message = stringResource(R.string.comment_sent))
        CommentRepliesAction.CommentSendingFailed ->
            ShowToast(message = stringResource(R.string.comment_sending_failed))
    }
}
