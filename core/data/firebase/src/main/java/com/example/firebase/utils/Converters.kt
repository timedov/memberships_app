package com.example.firebase.utils

import com.example.domain.model.CommentDomainModel
import com.example.domain.model.ContentType
import com.example.local.comment.entity.CommentEntity
import com.google.firebase.firestore.DocumentSnapshot

fun DocumentSnapshot.toCommentEntity(): CommentEntity? {
    val id = getString("id") ?: return null
    return CommentEntity(
        id = id,
        postId = getLong("postId") ?: -1L,
        username = getString("username"),
        profileImageUrl = getString("profileImageUrl"),
        postedAt = getLong("postedAt") ?: 0L,
        text = getString("text") ?: "",
        content = getString("content") ?: "",
        contentType = getLong("contentType")?.toInt() ?: ContentType.NONE.code
    )
}

fun CommentDomainModel.toFirestoreMap(): Map<String, Any?> {
    return mapOf(
        "id" to id,
        "postId" to postId,
        "username" to username,
        "postedAt" to postedAt,
        "text" to text,
        "content" to content,
        "contentType" to contentType.code
    )
}