package com.example.local.utils

import com.example.domain.model.CommentDomainModel
import com.example.domain.model.ContentType
import com.example.domain.model.ContentType.NONE
import com.example.domain.model.PostDataDomainModel
import com.example.local.comment.entity.CommentEntity
import com.example.local.post.entity.PostDataEntity
import com.example.local.post.entity.PostDraftEntity

fun PostDataEntity.toDomainModel() =
    PostDataDomainModel(
        id = id,
        title = title.orEmpty(),
        content = content.orEmpty(),
        contentType = ContentType.entries.find { it.code == contentType } ?: NONE,
        category = category.orEmpty(),
        postedAt = postedAt,
        author = author.orEmpty(),
        body = body.orEmpty(),
        requiresSubscription = requiresSubscription
    )

fun PostDataDomainModel.toDataEntity() =
    PostDataEntity(
        id = id,
        title = title,
        content = content,
        contentType = contentType.code,
        category = category,
        postedAt = postedAt,
        author = author,
        body = body,
        requiresSubscription = requiresSubscription
    )

fun PostDraftEntity.toDomainModel() =
    PostDataDomainModel(
        id = id,
        title = title.orEmpty(),
        content = content.orEmpty(),
        contentType = ContentType.entries.find { it.code == contentType } ?: NONE,
        category = category.orEmpty(),
        body = body.orEmpty(),
        requiresSubscription = requiresSubscription
    )

fun PostDataDomainModel.toDraftEntity() =
    PostDraftEntity(
        id = id,
        title = title,
        content = content,
        contentType = contentType.code,
        category = category,
        body = body,
        requiresSubscription = requiresSubscription
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
