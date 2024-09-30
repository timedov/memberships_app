package com.example.auth.presentation.login.model

sealed interface LoginAction {
    data class ShowMessage(val message: String) : LoginAction
}