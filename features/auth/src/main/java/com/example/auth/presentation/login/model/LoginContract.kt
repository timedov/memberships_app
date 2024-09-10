package com.example.auth.presentation.login.model

sealed interface LoginState {
    data object Initial : LoginState
    data object Loading : LoginState
    data object NotValidEmail : LoginState
    data object WrongPassword : LoginState
    data class Error(val error: Throwable) : LoginState
    data object UserNotFound : LoginState
}

sealed interface LoginEvent {
    data class LogInClick(val email: String, val password: String) : LoginEvent
    data object ForgotPasswordClick : LoginEvent
}

sealed interface LoginAction {
    data class ShowMessage(val message: String) : LoginAction
    object NavigateToHomeScreen : LoginAction
    object NavigateToForgotPasswordScreen : LoginAction
}