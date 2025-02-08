package com.example.feed.impl.data.utils

import com.example.feed.api.domain.model.PostDomainModel
import com.example.data.api.post.model.PostDataModel
import com.example.feed.api.domain.model.SavePostForm
import java.util.UUID

internal fun PostDomainModel.toDataModel(author: String = "") =
    PostDataModel(
        id = id,
        title = title,
        content = content,
        postedAt = if (postedAt == -1L) System.currentTimeMillis() else postedAt,
        author = author,
        body = body,
        requiresSubscription = requiresSubscription
    )

internal fun PostDataModel.toDomainModel() =
    PostDomainModel(
        id = id,
        title = title,
        content = content,
        profileImage = profileImage,
        postedAt = postedAt,
        author = author,
        body = body,
        requiresSubscription = requiresSubscription
    )

internal fun SavePostForm.toDataModel() =
    PostDataModel(
        id = id,
        title = title,
        content = content,
        body = body,
        author = author,
        postedAt = postedAt,
        requiresSubscription = requiresSubscription
    )