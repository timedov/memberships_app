package com.example.postdetails.api.presentation.utils

import com.example.postdetails.api.domain.model.CommentDomainModel
import com.example.postdetails.api.domain.model.PostStatsDomainModel
import com.example.ui.model.CommentUiModel
import com.example.ui.model.PostStatsUiModel
import com.example.ui.utils.statsCountToPrettyFormat
import com.example.ui.utils.timeShort

fun CommentDomainModel.toUiModel() =
    CommentUiModel(
        id = id,
        username = username,
        parentCommentId = parentCommentId,
        profileImageUrl = profileImageUrl.orEmpty(),
        postedWhen = postedAt.timeShort(),
        body = body
    )

fun PostStatsDomainModel.toUiModel() =
    PostStatsUiModel(
        favoriteCount = favoriteCount.statsCountToPrettyFormat(),
        commentsCount = commentsCount.statsCountToPrettyFormat()
    )