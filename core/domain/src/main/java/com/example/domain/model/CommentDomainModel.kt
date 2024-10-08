package com.example.domain.model

class CommentDomainModel(
    val id: Long = -1L,
    val username: String = "",
    val profileImageUrl: String? = null,
    val postedAt: Long = 0L,
    val body: String = "",
)