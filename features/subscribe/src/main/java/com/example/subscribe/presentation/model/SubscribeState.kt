package com.example.subscribe.presentation.model

import com.example.domain.model.TierModel

sealed interface SubscribeState {
    data object Loading : SubscribeState
    data class Content(val tiers: List<TierModel>, val isCurrentUser: Boolean) : SubscribeState
    data object Error : SubscribeState
}

