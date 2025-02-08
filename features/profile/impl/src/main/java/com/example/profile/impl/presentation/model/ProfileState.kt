package com.example.profile.impl.presentation.model

import androidx.paging.PagingData
import com.example.feed.api.domain.model.PostDomainModel
import com.example.ui.model.PostUiModel
import com.example.ui.model.UserUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

internal data class ProfileState(
    val isLoading: Boolean = true,
    val isRefreshing: Boolean = false,
    val username: String = "",
    val isCurrentUser: Boolean = false,
    val isSubscribed: Boolean = false,
    val userDetails: UserUiModel = UserUiModel(),
    val subscribers: String = "",
    val postsFlow: Flow<PagingData<PostUiModel>> = emptyFlow(),
    val isError: Boolean = false,
)
