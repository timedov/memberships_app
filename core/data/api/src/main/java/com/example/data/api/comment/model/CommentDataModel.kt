package com.example.data.api.comment.model

class CommentDataModel(
    val id: String = "",
    val postId: String = "",
    val parentCommentId: String = "",
    val username: String = "",
    val profileImageUrl: String? = null,
    val postedAt: Long = 0L,
    val body: String = "",
)