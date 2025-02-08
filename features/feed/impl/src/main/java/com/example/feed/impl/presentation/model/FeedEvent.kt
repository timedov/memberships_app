package com.example.feed.impl.presentation.model

internal sealed interface FeedEvent {
    data class ProfileClick(val username: String) : FeedEvent
    data class PostClick(val id: String) : FeedEvent
    data object Refresh : FeedEvent
}