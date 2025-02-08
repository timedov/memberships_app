package com.example.postdetails.api.domain.model

import java.util.UUID

class CommentDomainModel(
    val id: String = UUID.randomUUID().toString(),
    val postId: String = "",
    val parentCommentId: String = "",
    val username: String = "",
    val profileImageUrl: String? = null,
    val postedAt: Long = 0L,
    val body: String = "",
)