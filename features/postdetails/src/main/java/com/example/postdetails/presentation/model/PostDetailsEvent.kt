package com.example.postdetails.presentation.model

sealed interface PostDetailsEvent {

    data class Initiate(val postId: Long, val authorName: String) : PostDetailsEvent
    data object BackClick : PostDetailsEvent
    data object SubscribeClick : PostDetailsEvent
    data object FavoriteClick : PostDetailsEvent
    data class CommentValueChanged(val value: String) : PostDetailsEvent
    data class ProfileClick(val username: String) : PostDetailsEvent
    data class ReplyClick(val commentId: String) : PostDetailsEvent
    data object SendComment : PostDetailsEvent
    data object Refresh : PostDetailsEvent
}