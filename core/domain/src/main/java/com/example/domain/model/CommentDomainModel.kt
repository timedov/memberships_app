package com.example.domain.model

import java.util.UUID

class CommentDomainModel(
    val id: String = UUID.randomUUID().toString(),
    val postId: Long = -1L,
    val username: String = "",
    val profileImageUrl: String? = null,
    val postedAt: Long = 0L,
    val text: String = "",
    val content: String = "",
    val contentType: ContentType = ContentType.NONE,
)