package com.example.savetier.presentation.model

data class SaveTierState(
    val isLoading: Boolean = true,
    val tierId: Long = -1,
    val name: String = "",
    val nameError: String = "",
    val price: Double = 0.0,
    val priceError: String = "",
    val description: String = "",
    val descriptionError: String = "",
    val isError: Boolean = false
)
