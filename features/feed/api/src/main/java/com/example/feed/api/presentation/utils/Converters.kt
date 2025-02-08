package com.example.feed.api.presentation.utils

import com.example.feed.api.domain.model.PostDomainModel
import com.example.ui.model.PostUiModel
import com.example.ui.utils.timeAgo

fun PostDomainModel.toUiModel() =
    PostUiModel(
        id = id,
        title = title,
        content = content,
        profileImage = profileImage,
        postedAgo = postedAt.timeAgo(),
        author = author,
        body = body,
        requiresSubscription = requiresSubscription
    )

fun PostUiModel.toDomainModel() =
    PostDomainModel(
        id = id,
        title = title,
        content = content,
        profileImage = profileImage,
        postedAt = System.currentTimeMillis(),
        author = author,
        body = body,
        requiresSubscription = requiresSubscription
    )