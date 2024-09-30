package com.example.savetier.presentation.model

sealed interface SaveTierEvent {

    data object BackClick : SaveTierEvent
    data class NameChange(val name: String) : SaveTierEvent
    data class PriceChange(val price: Double) : SaveTierEvent
    data class DescriptionChange(val description: String) : SaveTierEvent
    data object SaveTier : SaveTierEvent
    data object RetryClick : SaveTierEvent
}
