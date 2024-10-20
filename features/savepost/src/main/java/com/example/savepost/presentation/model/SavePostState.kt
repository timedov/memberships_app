package com.example.savepost.presentation.model

data class SavePostState(
    val isLoading: Boolean = false,
    val title: String = "",
    val titleError: String = "",
    val description: String = "",
    val descriptionError: String = "",
    val isError: Boolean = false
)