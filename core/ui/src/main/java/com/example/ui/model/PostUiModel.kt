package com.example.ui.model

data class PostUiModel(
    val id: Long,
    val title: String,
    val image: String,
    val body: String,
    val category: String,
    val viewsCount: Int,
    val commentsCount: Int,
    val postedAgo: String,
    val author: String,
    val requiresSubscription: Boolean
)