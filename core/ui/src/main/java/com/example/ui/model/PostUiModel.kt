package com.example.ui.model

import java.util.UUID

class PostUiModel(
    val id: String = UUID.randomUUID().toString(),
    val title: String = "",
    val content: String = "",
    val profileImage: String = "",
    val postedAgo: String = "",
    val author: String = "",
    val body: String = "",
    val requiresSubscription: Boolean = false
)