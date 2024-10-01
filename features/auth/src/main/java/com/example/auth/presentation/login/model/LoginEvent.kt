package com.example.auth.presentation.login.model

sealed interface LoginEvent {
    data class LogInClick(val email: String, val password: String) : LoginEvent
    data object ForgotPasswordClick : LoginEvent
    data object SignUpClick : LoginEvent
}