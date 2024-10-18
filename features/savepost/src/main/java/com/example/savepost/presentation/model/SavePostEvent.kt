package com.example.savepost.presentation.model

sealed interface SavePostEvent {
    data object Initiate : SavePostEvent
    data object BackClick : SavePostEvent
    data class TitleChange(val title: String) : SavePostEvent
    data class DescriptionChange(val description: String) : SavePostEvent
    data object SavePost : SavePostEvent
    data object RetryClick : SavePostEvent
}
