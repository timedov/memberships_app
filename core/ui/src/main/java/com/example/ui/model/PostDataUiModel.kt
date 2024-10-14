package com.example.ui.model

class PostDataUiModel(
    val id: Long = -1L,
    val title: String = "",
    val content: String = "",
    val isVideo: Boolean = false,
    val category: String = "",
    val postedAgo: String = "",
    val author: String = "",
    val body: String = "",
    val requiresSubscription: Boolean = false
)