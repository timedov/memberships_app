package com.example.savetier.presentation.model

sealed interface SaveTierState {

    data object Loading : SaveTierState
    data class Form(
        val name: String = "",
        val nameError: String? = null,
        val price: Double = 0.0,
        val priceError: String? = null,
        val description: String = "",
        val descriptionError: String? = null,
    ) : SaveTierState
    data object Error : SaveTierState
}
