package com.example.savepost.presentation.model

sealed interface SavePostEvent {
    data object Initiate : SavePostEvent
    data object BackClick : SavePostEvent
    data class TitleValueChange(val title: String) : SavePostEvent
    data class DescriptionValueChange(val description: String) : SavePostEvent
    data object SavePost : SavePostEvent
}
