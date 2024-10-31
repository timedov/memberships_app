package com.example.domain.model

class PostDataDomainModel(
    val id: Long = -1L,
    val title: String = "",
    val content: String = "",
    val contentType: ContentType = ContentType.NONE,
    val category: String = "",
    val postedAt: Long = -1L,
    val author: String = "",
    val body: String = "",
    val requiresSubscription: Boolean = false
)