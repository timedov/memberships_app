package com.example.subscribe.presentation.model

sealed interface SubscribeEvent {
    data class Subscribe(val tierId: Long) : SubscribeEvent
    data class EditTier(val tierId: Long) : SubscribeEvent
    data object AddNewTier : SubscribeEvent
    data object BackClick : SubscribeEvent
    data object Refresh : SubscribeEvent
}