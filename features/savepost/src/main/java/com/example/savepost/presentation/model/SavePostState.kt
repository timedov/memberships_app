package com.example.savepost.presentation.model

import android.net.Uri
import com.example.domain.model.ContentType

data class SavePostState(
    val isLoading: Boolean = false,
    val title: String = "",
    val titleError: String = "",
    val content: Uri = Uri.EMPTY,
    val contentType: ContentType = ContentType.NONE,
    val description: String = "",
    val descriptionError: String = "",
    val requiresSubscription: Boolean = false,
    val isError: Boolean = false
)