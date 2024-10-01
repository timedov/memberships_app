package com.example.profile.presentation.model

sealed interface ProfileEvent {
    data object Refresh : ProfileEvent
    data object SubscribeClick : ProfileEvent
    data class PostClick(val postId: Long) : ProfileEvent
    data object BackClick: ProfileEvent
}
