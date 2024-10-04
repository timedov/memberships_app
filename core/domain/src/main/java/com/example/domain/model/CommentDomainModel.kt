package com.example.domain.model

class CommentDomainModel(
    val id: Long,
    val username: String,
    val profileImageUrl: String,
    val postedAt: Long,
    val body: String,
)