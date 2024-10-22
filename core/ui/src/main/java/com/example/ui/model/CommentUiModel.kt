package com.example.ui.model

class CommentUiModel(
    val username: String = "",
    val parentCommentId: String = "",
    val profileImageUrl: String = "",
    val postedWhen: String = "",
    val body: CommentBodyUiModel = CommentBodyUiModel(),
)
