package com.example.data.impl.local.post.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "post_drafts")
class PostDraftEntity(
    @PrimaryKey val id: String,
    val title: String?,
    val content: String?,
    val body: String?,
    val requiresSubscription: Boolean
)