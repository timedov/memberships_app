package com.example.commentreplies.impl.presentation.model

internal sealed interface CommentRepliesEvent {

    data class Initiate(val commentId: String) : CommentRepliesEvent
    data object BackClick : CommentRepliesEvent
    data class ProfileClick(val username: String) : CommentRepliesEvent
    data class CommentValueChanged(val value: String) : CommentRepliesEvent
    data object SendComment : CommentRepliesEvent
    data object Refresh : CommentRepliesEvent
}