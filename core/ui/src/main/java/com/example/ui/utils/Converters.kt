package com.example.ui.utils

import android.icu.util.Calendar
import com.example.domain.model.PostDomainModel
import com.example.ui.model.PostUiModel

fun PostDomainModel.toUiModel() = PostUiModel(
    id = id,
    title = title,
    image = image,
    category = category,
    postedAgo = postedAt.timeAgo(),
    author = author
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
