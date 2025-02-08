package com.example.signin.impl.presentation.model

internal data class LoginState(
    val email: String = "",
    val password: String = "",
    val isInvalidCredentials: Boolean = false,
    val isLoading: Boolean = false,
)