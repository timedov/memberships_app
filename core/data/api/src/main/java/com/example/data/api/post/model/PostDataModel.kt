package com.example.data.api.post.model

class PostDataModel(
    val id: String = "",
    val title: String = "",
    val content: String = "",
    val profileImage: String = "",
    val postedAt: Long = -1L,
    val author: String = "",
    val body: String = "",
    val requiresSubscription: Boolean = false
)