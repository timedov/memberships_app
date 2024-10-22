package com.example.commentreplies.presentation.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.commentreplies.R
import com.example.commentreplies.presentation.model.CommentRepliesAction
import com.example.ui.view.composables.ShowToast

@Composable
fun ObserveActions(action: CommentRepliesAction) {

    when (action) {
        CommentRepliesAction.Initiate -> Unit
        CommentRepliesAction.CommentSent ->
            ShowToast(message = stringResource(R.string.comment_sent_successfully))
        CommentRepliesAction.CommentSendingFailed ->
            ShowToast(message = stringResource(R.string.comment_sending_failed))
    }
}
