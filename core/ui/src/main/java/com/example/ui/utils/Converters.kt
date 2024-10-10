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
import com.example.ui.model.CommentBodyUiModel
import com.example.ui.model.CommentUiModel
import com.example.ui.model.PostDataUiModel
import com.example.ui.model.PostStatsUiModel
import com.example.ui.model.PostUiModel

// Converters

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
        profileImageUrl = profileImageUrl.orEmpty(),
        postedWhen = postedAt.timeShort(),
        body = CommentBodyUiModel(
            text = text,
            content = content,
            isVideo = isVideo,
        ),
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

// Utils

private fun Long.getTimeDifference(): Pair<Long, String> {
    val now = Calendar.getInstance().timeInMillis
    val diffInMillis = now - this

    val seconds = diffInMillis / 1000
    val minutes = seconds / 60
    val hours = minutes / 60
    val days = hours / 24

    return when {
        seconds < 60 -> seconds to "s"
        minutes < 60 -> minutes to "m"
        hours < 24 -> hours to "h"
        days < 30 -> days to "d"
        days < 365 -> days / 30 to "m"
        else -> days / 365 to "y"
    }
}

fun Long.timeShort(): String {
    val (value, unit) = getTimeDifference()
    return "$value$unit"
}

fun Long.timeAgo(): String {
    val (value, unit) = getTimeDifference()
    val unitFull = when (unit) {
        "s" -> "seconds"
        "m" -> "minutes"
        "h" -> "hours"
        "d" -> "days"
        "y" -> "years"
        else -> "months"
    }
    return "$value $unitFull ago"
}

fun Int.subscribersCountToPrettyFormat() =
    when {
        this < 1000 -> this.toString()
        this < 1000000 -> {"${this / 1000}k"}
        else -> {"${this / 1000000}m"}
    }

fun Int.statsCountToPrettyFormat() =
    when {
        this < 1000 -> this.toString()
        this < 1000000 -> {"${this / 1000}k"}
        else -> {"${this / 1000000}m"}
    }
