package com.example.feed.impl.presentation.model

import androidx.paging.PagingData
import com.example.ui.model.PostUiModel
import com.example.ui.model.UserUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class FeedState(
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val postsFlow: Flow<PagingData<PostUiModel>> = emptyFlow(),
    val userDetails: UserUiModel = UserUiModel(),
    val isError: Boolean = false,
    //val selectedTier: TierType = TierType.ALL_TIERS,
)
