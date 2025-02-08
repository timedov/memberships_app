package com.example.feed.api.domain.model

import java.util.UUID

class PostDomainModel(
    val id: String = UUID.randomUUID().toString(),
    val title: String = "",
    val content: String = "",
    val profileImage: String = "",
    val postedAt: Long = -1L,
    val author: String = "",
    val body: String = "",
    val requiresSubscription: Boolean = false
)