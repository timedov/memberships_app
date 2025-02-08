package com.example.savepost.impl.presentation.model

internal data class SavePostState(
    val isLoading: Boolean = false,
    val title: String = "",
    val titleError: String = "",
    val content: String = "",
    val description: String = "",
    val descriptionError: String = "",
    val requiresSubscription: Boolean = false,
    val isError: Boolean = false
)