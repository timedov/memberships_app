package com.example.commentreplies.impl.presentation.model

internal sealed interface CommentRepliesAction {

    data object Initiate: CommentRepliesAction
    data object CommentSent : CommentRepliesAction
    data object CommentSendingFailed : CommentRepliesAction
}
