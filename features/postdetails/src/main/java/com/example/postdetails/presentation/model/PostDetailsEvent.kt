package com.example.postdetails.presentation.model

sealed interface PostDetailsEvent {

    data object BackClick : PostDetailsEvent
    data class FavoriteClick(val isFavorite: Boolean) : PostDetailsEvent
    data class ProfileClick(val username: String) : PostDetailsEvent
    data class SendComment(val text: String) : PostDetailsEvent
    data object Refresh : PostDetailsEvent
}