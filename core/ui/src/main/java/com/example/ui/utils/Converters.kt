package com.example.ui.utils

import com.example.domain.model.CommentDomainModel
import com.example.domain.model.ContentType
import com.example.domain.model.PostDomainModel
import com.example.domain.model.TierDomainModel
import com.example.domain.model.UserDetailsDomainModel
import com.example.ui.model.CommentBodyUiModel
import com.example.ui.model.CommentUiModel
import com.example.ui.model.PostUiModel
import com.example.ui.model.TierUiModel
import com.example.ui.model.UserDetailsUiModel

fun TierDomainModel.toUiModel() =
    TierUiModel(
        id = id,
        name = name,
        price = "$$price",
        description = description
    )
fun PostDomainModel.toUiModel() = PostUiModel(
    id = id,
    title = title,
    image = image,
    category = category,
    postedAgo = postedAt.timeAgo(),
    author = author
)

fun TierUiModel.toDomainModel() =
    TierDomainModel(
        id = id,
        name = name,
        price = price.removePrefix("$").toDouble(),
        description = description
    )

fun UserDetailsDomainModel.toUiModel() =
    UserDetailsUiModel(
        username = username,
        imageUrl = imageUrl,
        subscribers = subscribers,
        joinedYear = joinedYear,
        about = about
    )


fun CommentDomainModel.toUiModel() =
    CommentUiModel(
        username = username,
        parentCommentId = parentCommentId,
        profileImageUrl = profileImageUrl.orEmpty(),
        postedWhen = postedAt.timeShort(),
        body = CommentBodyUiModel(
            text = text,
            content = content,
            isVideo = contentType == ContentType.VIDEO,
        ),
    )
