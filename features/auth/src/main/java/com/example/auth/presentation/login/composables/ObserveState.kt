package com.example.auth.presentation.login.composables

import androidx.compose.runtime.Composable
import com.example.auth.presentation.login.model.LoginState
import com.example.ui.view.composables.LoadingScreen

@Composable
fun ObserveState(
    uiState: LoginState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onForgotPasswordClick: () -> Unit,
    onSignUpClick: () -> Unit,
    onLogInClick: () -> Unit,
) {
    LoginContent(
        email = uiState.email,
        password = uiState.password,
        isInvalidCredentials = uiState.isInvalidCredentials,
        onEmailChange = onEmailChange,
        onPasswordChange = onPasswordChange,
        onForgotPasswordClick = onForgotPasswordClick,
        onSignUpClick = onSignUpClick,
        onLogInClick = onLogInClick)

    LoadingScreen(isLoading = uiState.isLoading)
}
