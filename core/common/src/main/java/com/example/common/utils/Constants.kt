package com.example.common.utils

object Constants {
    const val CACHE_MAX_SIZE : Long = 10 * 1024 * 1024 // 10 MB
    const val CACHE_EXPIRATION = 120 // 120 minutes
    const val JSON_CONTENT_TYPE = "application/json"
    const val IMAGE_CONTENT_TYPE_PREFIX = "image/"
    const val VIDEO_CONTENT_TYPE_PREFIX = "video/"
    const val IMAGE_MIME_TYPE = "image/*"
    const val VIDEO_MIME_TYPE = "video/*"
    const val DEFAULT_PAGE_SIZE = 10
    const val MAX_TIER_DESCRIPTION_LENGTH = 100
    const val MIN_POST_TITLE_LENGTH = 3
    const val MAX_POST_TITLE_LENGTH = 20
    const val MAX_POST_DESCRIPTION_LENGTH = 500
}