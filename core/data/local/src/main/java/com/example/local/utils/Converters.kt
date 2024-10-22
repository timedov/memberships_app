package com.example.local.utils

import com.example.domain.model.CommentDomainModel
import com.example.domain.model.ContentType
import com.example.domain.model.ContentType.NONE
import com.example.domain.model.PostDataDomainModel
import com.example.local.comment.entity.CommentEntity
import com.example.local.post.entity.PostEntity

fun PostEntity.toDomainModel() =
    PostDataDomainModel(
        id = id,
        title = title.orEmpty(),
        content = content.orEmpty(),
        isVideo = isVideo,
        category = category.orEmpty(),
        postedAt = postedAt,
        author = author.orEmpty(),
        body = body.orEmpty(),
        isPaid = requiresSubscription
    )

fun PostDataDomainModel.toEntity() =
    PostEntity(
        id = id,
        title =title,
        content = content,
        isVideo = isVideo,
        category = category,
        postedAt = postedAt,
        author = author,
        body = body,
        requiresSubscription = isPaid
    )

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
        parentCommentId = parentCommentId,
        username = username,
        profileImageUrl = profileImageUrl,
        postedAt = postedAt,
        text = text,
        content = content,
        contentType = contentType.code,
    )
