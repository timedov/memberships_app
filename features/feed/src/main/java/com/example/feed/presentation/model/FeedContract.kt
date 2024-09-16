package com.example.feed.presentation.model

import androidx.paging.PagingData
import com.example.domain.model.PostModel
import com.example.domain.model.Tier

sealed interface FeedState {
    data object Loading: FeedState
    data class Content(val posts: PagingData<PostModel>, val selectedTier: Tier): FeedState
    data class Error(val error: Throwable): FeedState
}

sealed interface FeedEvent {
    data object ProfileClick: FeedEvent
    data class TierClick(val tier: Tier): FeedEvent
    data class PostClick(val id: Long): FeedEvent
    data object Refresh : FeedEvent
}

sealed interface FeedAction {
    data class ShowMessage(val message: String): FeedAction
}