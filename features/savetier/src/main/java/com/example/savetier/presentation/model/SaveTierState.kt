package com.example.savetier.presentation.model

data class SaveTierState(
    val isLoading: Boolean = true,
    val tierId: Long = -1,
    val name: String = "",
    val nameError: String? = null,
    val price: Double = 0.0,
    val priceError: String? = null,
    val description: String = "",
    val descriptionError: String? = null,
    val isError: Boolean = false
)
