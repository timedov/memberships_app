package com.example.ui.model

import com.example.domain.model.ContentType

class PostDataUiModel(
    val id: Long = -1L,
    val title: String = "",
    val content: String = "",
    val contentType: ContentType = ContentType.NONE,
    val category: String = "",
    val postedAgo: String = "",
    val author: String = "",
    val body: String = "",
    val requiresSubscription: Boolean = false
)