package com.example.local.utils

import com.example.domain.model.PostDataDomainModel
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
        isPaid = this.isPaid
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
        isPaid = this.isPaid
    )
}
