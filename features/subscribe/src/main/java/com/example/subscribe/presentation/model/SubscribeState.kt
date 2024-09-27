package com.example.subscribe.presentation.model

import com.example.ui.model.TierUiModel
import com.example.ui.model.UserDetailsUiModel

sealed interface SubscribeState {
    data object Loading : SubscribeState
    data class Content(
        val userDetails: UserDetailsUiModel,
        val tiers: List<TierUiModel>,
        val isCurrentUser: Boolean
    ) : SubscribeState
    data object Error : SubscribeState
}

