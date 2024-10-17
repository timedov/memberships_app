package com.example.subscribe.presentation.model

import com.example.ui.model.TierUiModel
import com.example.ui.model.UserDetailsUiModel

data class SubscribeState(
    val isLoading: Boolean = true,
    val isRefreshing: Boolean = false,
    val username: String = "",
    val userDetails: UserDetailsUiModel = UserDetailsUiModel(),
    val tiers: List<TierUiModel> = emptyList(),
    val isCurrentUser: Boolean = false,
    val selectedTierId: Long = -1L,
    val isError: Boolean = false
)

