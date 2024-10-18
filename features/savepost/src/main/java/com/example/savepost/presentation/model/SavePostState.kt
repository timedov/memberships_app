package com.example.savepost.presentation.model

data class SavePostState(
    val isLoading: Boolean = false,
    val title: String = "",
    val titleError: String? = null,
    val description: String = "",
    val descriptionError: String? = null,
    val isError: Boolean = false
)