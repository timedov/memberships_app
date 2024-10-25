package com.example.savepost.presentation.model

import android.net.Uri
import com.example.domain.model.ContentType

sealed interface SavePostEvent {
    data class Initiate(val loadDraft: Boolean) : SavePostEvent
    data object BackClick : SavePostEvent
    data class TitleValueChange(val title: String) : SavePostEvent
    data class ContentValueChange(val uri: Uri, val contentType: ContentType) : SavePostEvent
    data class DescriptionValueChange(val description: String) : SavePostEvent
    data class RequireSubscriptionChange(val requiresSubscription: Boolean) : SavePostEvent
    data object SavePost : SavePostEvent
    data object Retry : SavePostEvent
}
