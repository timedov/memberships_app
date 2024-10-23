package com.example.savepost.usecase

import android.content.ContentResolver
import android.net.Uri
import com.example.common.utils.Constants
import com.example.domain.model.ContentType
import javax.inject.Inject

class GetContentTypeUseCase @Inject constructor(
    private val contentResolver: ContentResolver
) {

    operator fun invoke(uri: Uri): ContentType =
        contentResolver.getType(uri)?.let {
            when {
                it.startsWith(Constants.IMAGE_CONTENT_TYPE_PREFIX) -> ContentType.IMAGE
                it.startsWith(Constants.VIDEO_CONTENT_TYPE_PREFIX) -> ContentType.VIDEO
                else -> ContentType.NONE
            }
        } ?: ContentType.NONE
}
