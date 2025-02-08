package com.example.feed.api.domain.model

class SavePostForm(
    val id: String,
    val title: String,
    val content: String = "",
    val postedAt: Long,
    val author: String,
    val body: String = "",
    val requiresSubscription: Boolean = false
)