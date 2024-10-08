package com.example.ui.utils

import com.example.domain.model.TierDomainModel
import com.example.domain.model.UserDetailsDomainModel
import com.example.ui.model.TierUiModel
import com.example.ui.model.UserDetailsUiModel
import android.icu.util.Calendar
import com.example.domain.model.CommentDomainModel
import com.example.domain.model.PostDataDomainModel
import com.example.domain.model.PostDomainModel
import com.example.domain.model.PostStatsDomainModel
import com.example.ui.model.CommentUiModel
import com.example.ui.model.PostDataUiModel
import com.example.ui.model.PostStatsUiModel
import com.example.ui.model.PostUiModel

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

fun Long.timeAgo(): String {
    val now = Calendar.getInstance().timeInMillis
    val diffInMillis = now - this

    val seconds = diffInMillis / 1000
    val minutes = seconds / 60
    val hours = minutes / 60
    val days = hours / 24

    return when {
        seconds < 60 -> "$seconds seconds ago"
        minutes < 60 -> "$minutes minutes ago"
        hours < 24 -> "$hours hours ago"
        days < 30 -> "$days days ago"
        days < 365 -> "${days / 30} months ago"
        else -> "${days / 365} years ago"
    }
}

fun Int.subscribersCountToPrettyFormat() =
    when {
        this < 1000 -> this.toString()
        this < 1000000 -> {"${this / 1000}k"}
        else -> {"${this / 1000000}m"}
    }

fun UserDetailsDomainModel.toUiModel() =
    UserDetailsUiModel(
        username = username,
        imageUrl = imageUrl,
        subscribers = subscribers,
        joinedYear = joinedYear,
        about = about
    )

fun Long.timeShort(): String {
    val now = Calendar.getInstance().timeInMillis
    val diffInMillis = now - this

    val seconds = diffInMillis / 1000
    val minutes = seconds / 60
    val hours = minutes / 60
    val days = hours / 24

    return when {
        seconds < 60 -> "${seconds}s"
        minutes < 60 -> "${minutes}m"
        hours < 24 -> "${hours}h"
        days < 30 -> "${days}d"
        days < 365 -> "${days / 30}m"
        else -> "${days / 365}y"
    }
}


fun CommentDomainModel.toUiModel() =
    CommentUiModel(
        username = username,
        profileImageUrl = profileImageUrl.orEmpty(),
        postedWhen = postedAt.timeShort(),
        body = body
    )

fun PostDataDomainModel.toUiModel() =
    PostDataUiModel(
        title = title,
        content = content,
        isVideo = isVideo,
        category = category,
        postedAgo = postedAt.timeShort(),
        author = author,
        body = body,
        isPaid = isPaid
    )

fun PostStatsDomainModel.toUiModel() =
    PostStatsUiModel(
        favoriteCount = favoriteCount.statsCountToPrettyFormat(),
        commentsCount = commentsCount.statsCountToPrettyFormat()
    )

fun Int.statsCountToPrettyFormat() =
    when {
        this < 1000 -> this.toString()
        this < 1000000 -> {"${this / 1000}k"}
        else -> {"${this / 1000000}m"}
    }
