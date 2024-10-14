package com.example.local.utils

import com.example.domain.model.CommentDomainModel
import com.example.domain.model.ContentType
import com.example.domain.model.ContentType.NONE
import com.example.domain.model.PostDataDomainModel
import com.example.local.comment.entity.CommentEntity
import com.example.local.post.entity.PostEntity

fun PostEntity.toDomainModel(): PostDataDomainModel {
    return PostDataDomainModel(
        id = this.id,
        title = this.title.orEmpty(),
        content = this.content.orEmpty(),
        isVideo = this.isVideo,
        category = this.category.orEmpty(),
        postedAt = this.postedAt,
        author = this.author.orEmpty(),
        body = this.body.orEmpty(),
        isPaid = this.requiresSubscription
    )
}

fun PostDataDomainModel.toEntity(): PostEntity {
    return PostEntity(
        id = this.id,
        title = this.title,
        content = this.content,
        isVideo = this.isVideo,
        category = this.category,
        postedAt = this.postedAt,
        author = this.author,
        body = this.body,
        requiresSubscription = this.isPaid
    )
}

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
