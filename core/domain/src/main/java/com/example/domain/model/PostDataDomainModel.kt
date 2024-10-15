package com.example.domain.model

class PostDataDomainModel(
    val id: Long,
    val title: String,
    val content: String,
    val isVideo: Boolean,
    val category: String,
    val postedAt: Long,
    val author: String,
    val body: String,
    val isPaid: Boolean
)