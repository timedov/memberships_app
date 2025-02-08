package com.example.postdetails.impl.presentation.model

internal sealed interface PostDetailsAction {

    data object Initiate: PostDetailsAction
    data object SetFavoriteFailed : PostDetailsAction
    data object CommentSent : PostDetailsAction
    data object CommentSendingFailed : PostDetailsAction
}
