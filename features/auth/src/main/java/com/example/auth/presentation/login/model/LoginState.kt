package com.example.auth.presentation.login.model

data class LoginState(
    val email: String = "",
    val password: String = "",
    val isInvalidCredentials: Boolean = false,
    val isLoading: Boolean = false,
)