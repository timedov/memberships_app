package com.example.auth.presentation.login.model

sealed interface LoginState {
    data object Initial : LoginState
    data object Loading : LoginState
    data object InvalidCredentials : LoginState
    data class Error(val error: Throwable) : LoginState
}