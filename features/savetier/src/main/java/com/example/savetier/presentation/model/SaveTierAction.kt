package com.example.savetier.presentation.model

sealed interface SaveTierAction {

    data object Initiate : SaveTierAction
    data object SaveSuccess : SaveTierAction
    data object SaveError : SaveTierAction
}