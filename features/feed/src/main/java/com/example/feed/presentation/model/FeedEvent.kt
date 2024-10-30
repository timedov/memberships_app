package com.example.feed.presentation.model

import com.example.common.model.TierType

sealed interface FeedEvent {
    data object ProfileClick: FeedEvent
    data class TierClick(val tier: TierType): FeedEvent
    data class PostClick(val id: Long): FeedEvent
    data object Refresh : FeedEvent
}