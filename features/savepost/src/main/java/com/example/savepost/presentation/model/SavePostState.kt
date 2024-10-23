package com.example.savepost.presentation.model

data class SavePostState(
    val isLoading: Boolean = false,
    val title: String = "",
    val titleError: String = "",
    val content: String = "",
    val isVideo: Boolean = false,
    val description: String = "",
    val descriptionError: String = "",
    val requiresSubscription: Boolean = false,
    val isError: Boolean = false
)