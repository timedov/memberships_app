package com.example.postdetails.impl.data.utils

import com.example.postdetails.api.domain.model.CommentDomainModel
import com.example.data.api.comment.model.CommentDataModel
import com.example.postdetails.api.domain.model.FavoriteDomainModel
import com.example.data.api.favorite.model.FavoriteDataModel

internal fun CommentDataModel.toDomainModel(): CommentDomainModel =
    CommentDomainModel(
        id = id,
        postId = postId,
        parentCommentId = parentCommentId,
        username = username,
        profileImageUrl = profileImageUrl,
        postedAt = postedAt,
        body = body,
    )

internal fun CommentDomainModel.toDataModel(): CommentDataModel =
    CommentDataModel(
        id = id,
        postId = postId,
        parentCommentId = parentCommentId,
        username = username,
        profileImageUrl = profileImageUrl,
        postedAt = postedAt,
        body = body,
    )

internal fun FavoriteDomainModel.toDataModel() =
    FavoriteDataModel(
        id = id,
        postId = postId,
        username = username,
        isFavorite = isFavorite
    )