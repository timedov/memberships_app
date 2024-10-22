package com.example.ui.model

class CommentUiModel(
    val id: String = "",
    val username: String = "",
    val parentCommentId: String = "",
    val profileImageUrl: String = "",
    val postedWhen: String = "",
    val body: CommentBodyUiModel = CommentBodyUiModel(),
)
