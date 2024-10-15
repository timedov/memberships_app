package com.example.commentreplies.presentation.model

sealed interface CommentRepliesAction {

    data object Initiate: CommentRepliesAction
    data object CommentSent : CommentRepliesAction
    data object CommentSendingFailed : CommentRepliesAction
}
