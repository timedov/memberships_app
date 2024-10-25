package com.example.savepost.presentation.model

sealed interface SavePostAction {
    data object Initiate : SavePostAction
    data object SaveError : SavePostAction
}
