package com.example.domain.utils

import com.example.domain.model.PostDomainModel
import com.example.domain.model.PostWithStatsDomainModel

fun PostDomainModel.toPostWithStatsDomainModel(commentsCount: Int) =
    PostWithStatsDomainModel(
        id = id,
        title = title,
        image = image,
        body = body,
        category = category,
        viewsCount = viewsCount,
        commentsCount = commentsCount,
        postedAt = postedAt,
        author = author,
        requiresSubscription = requiresSubscription
    )