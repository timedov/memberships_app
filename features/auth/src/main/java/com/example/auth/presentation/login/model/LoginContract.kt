package com.example.auth.presentation.login.model

sealed interface LoginState {
    data object Initial : LoginState
    data object Loading : LoginState
    data object InvalidCredentials : LoginState
    data class Error(val error: Throwable) : LoginState
}

sealed interface LoginEvent {
    data class LogInClick(val email: String, val password: String) : LoginEvent
    data object ForgotPasswordClick : LoginEvent
    data object SignUpClick : LoginEvent
}

sealed interface LoginAction {
    data class ShowMessage(val message: String) : LoginAction
}