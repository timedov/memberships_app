package com.example.local.comment.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comments")
class CommentEntity(
    @PrimaryKey val id: String,
    val postId: Long = -1L,
    val parentCommentId: String?,
    val username: String?,
    val profileImageUrl: String?,
    val postedAt: Long = -1L,
    val text: String?,
    val content: String?,
    val contentType: Int,
    val cachedAt: Long = System.currentTimeMillis()
)