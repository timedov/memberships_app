package com.example.domain.model

class PostWithStatsDomainModel(
    val id: Long,
    val title: String,
    val image: String,
    val body: String,
    val category: String,
    val viewsCount: Int,
    val commentsCount: Int,
    val postedAt: Long,
    val author: String,
    val requiresSubscription: Boolean
)