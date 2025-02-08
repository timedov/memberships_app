package com.example.savepost.impl.presentation.model

sealed class SavePostEvent {

    data object RestoreDraft : SavePostEvent()
    data object BackClick : SavePostEvent()
    data class TitleValueChange(val title: String) : SavePostEvent()
    data class ContentValueChange(val content: String) : SavePostEvent()
    data class DescriptionValueChange(val description: String) : SavePostEvent()
    data class RequireSubscriptionChange(val requiresSubscription: Boolean) : SavePostEvent()
    data object SavePost : SavePostEvent()
    data object RemoveDraft : SavePostEvent()
}
