package com.example.subscribe.presentation.model

sealed interface SubscribeEvent {
    data class Initiate(val username: String) : SubscribeEvent
    data class SelectedTierChange(val tierId: Long) : SubscribeEvent
    data object Subscribe : SubscribeEvent
    data object EditTier : SubscribeEvent
    data object AddNewTier : SubscribeEvent
    data object BackClick : SubscribeEvent
    data object Refresh : SubscribeEvent
}