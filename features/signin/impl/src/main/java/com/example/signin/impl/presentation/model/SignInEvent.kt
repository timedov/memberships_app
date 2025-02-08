package com.example.signin.impl.presentation.model

internal sealed interface SignInEvent {
    data object SignInClick : SignInEvent
    data object ForgotPasswordClick : SignInEvent
    data object SignUpClick : SignInEvent
    data class EmailChanged(val email: String) : SignInEvent
    data class PasswordChanged(val password: String) : SignInEvent
}