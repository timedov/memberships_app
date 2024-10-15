package com.example.ui.utils

import android.icu.util.Calendar
import androidx.media3.common.MediaItem

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

fun String.urlToMediaItem() =
    MediaItem.fromUri(this)
