package com.example.savepost.presentation.model

import com.example.domain.model.ContentType
import com.example.ui.player.MediaPlayer

data class SavePostState(
    val isLoading: Boolean = false,
    val title: String = "",
    val titleError: String = "",
    val content: String = "",
    val contentType: ContentType = ContentType.NONE,
    val description: String = "",
    val descriptionError: String = "",
    val player: MediaPlayer,
    val requiresSubscription: Boolean = false,
    val isError: Boolean = false
)