package com.example.ui.model

data class PostUiModel(
    val id: Long,
    val title: String,
    val image: String,
    val category: String,
    val postedAgo: String,
    val author: String
)