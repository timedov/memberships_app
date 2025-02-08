package com.example.data.impl.local.utils

import com.example.data.api.post.model.PostDataModel
import com.example.data.impl.local.post.entity.PostDraftEntity

fun PostDraftEntity.toDomainModel() =
    PostDataModel(
        id = id,
        title = title.orEmpty(),
        content = content.orEmpty(),
        body = body.orEmpty(),
        requiresSubscription = requiresSubscription
    )

fun PostDataModel.toDraftEntity() =
    PostDraftEntity(
        id = id,
        title = title,
        content = content,
        body = body,
        requiresSubscription = requiresSubscription
    )
