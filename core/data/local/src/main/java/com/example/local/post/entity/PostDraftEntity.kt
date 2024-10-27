package com.example.local.post.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "post_drafts")
class PostDraftEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val title: String?,
    val content: String?,
    val contentType: Int,
    val category: String?,
    val body: String?,
    val requiresSubscription: Boolean
)