package com.example.local.post.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class PostDataEntity(
    @PrimaryKey val id: Long,
    val title: String?,
    val content: String?,
    val contentType: Int,
    val category: String?,
    val postedAt: Long,
    val author: String?,
    val body: String?,
    val requiresSubscription: Boolean
)