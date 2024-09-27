package com.example.subscribe.presentation.model

import com.example.ui.model.TierUiModel

sealed interface SubscribeState {
    data object Loading : SubscribeState
    data class Content(val tiers: List<TierUiModel>, val isCurrentUser: Boolean) : SubscribeState
    data object Error : SubscribeState
}

