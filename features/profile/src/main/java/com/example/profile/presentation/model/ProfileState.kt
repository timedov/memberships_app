package com.example.profile.presentation.model

import androidx.paging.PagingData
import com.example.domain.model.PostDomainModel
import com.example.ui.model.UserDetailsUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class ProfileState(
    val isLoading: Boolean = true,
    val isRefreshing: Boolean = false,
    val username: String = "",
    val isCurrentUser: Boolean = false,
    val isSubscribed: Boolean = false,
    val userDetails: UserDetailsUiModel = UserDetailsUiModel(),
    val subscribers: String = "",
    val postsFlow: Flow<PagingData<PostDomainModel>> = emptyFlow(),
    val isError: Boolean = false,
)
