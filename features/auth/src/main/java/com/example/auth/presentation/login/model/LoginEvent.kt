package com.example.auth.presentation.login.model

sealed interface LoginEvent {
    data object LogInClick : LoginEvent
    data object ForgotPasswordClick : LoginEvent
    data object SignUpClick : LoginEvent
    data class EmailChanged(val email: String) : LoginEvent
    data class PasswordChanged(val password: String) : LoginEvent
}