package com.example.feed.presentation.model

import androidx.paging.PagingData
import com.example.domain.model.PostDomainModel
import com.example.domain.model.TierType

sealed interface FeedState {
    data object Loading: FeedState
    data class Content(
        val posts: PagingData<PostDomainModel>,
        val selectedTier: TierType
    ): FeedState
    data class Error(val error: Throwable): FeedState
}
