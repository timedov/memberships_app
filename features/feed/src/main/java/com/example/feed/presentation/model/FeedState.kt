package com.example.feed.presentation.model

import androidx.paging.PagingData
import com.example.common.model.TierType
import com.example.domain.model.PostWithStatsDomainModel

data class FeedState(
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val posts: PagingData<PostWithStatsDomainModel> = PagingData.empty(),
    val selectedTier: TierType = TierType.ALL_TIERS,
)
