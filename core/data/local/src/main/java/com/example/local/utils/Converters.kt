package com.example.local.utils

import com.example.domain.model.CommentDomainModel
import com.example.domain.model.ContentType
import com.example.domain.model.ContentType.NONE
import com.example.local.comment.entity.CommentEntity

fun CommentEntity.toDomainModel() =
    CommentDomainModel(
        id = id,
        postId = postId,
        username = username.orEmpty(),
        profileImageUrl = profileImageUrl,
        postedAt = postedAt,
        text = text.orEmpty(),
        content = content.orEmpty(),
        contentType = ContentType.entries.find { it.code == contentType } ?: NONE
    )

fun CommentDomainModel.toEntity() =
    CommentEntity(
        id = id,
        postId = postId,
        username = username,
        profileImageUrl = profileImageUrl,
        postedAt = postedAt,
        text = text,
        content = content,
        contentType = contentType.code,
    )
