package com.example.profile.impl.presentation.model

internal sealed interface ProfileEvent {
    data class Initiate(val username: String) : ProfileEvent
    data object Refresh : ProfileEvent
    data object SubscribeClick : ProfileEvent
    data class PostClick(val postId: String) : ProfileEvent
    data object BackClick: ProfileEvent
    data object SignOut: ProfileEvent
}
