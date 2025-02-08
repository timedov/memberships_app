package com.example.signin.impl.presentation.composable

import androidx.compose.runtime.Composable
import com.example.signin.impl.presentation.model.LoginState
import com.example.ui.view.composable.LoadingScreen

@Composable
internal fun ObserveState(
    uiState: LoginState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onForgotPasswordClick: () -> Unit,
    onSignUpClick: () -> Unit,
    onLogInClick: () -> Unit,
) {
    SignInContent(
        email = uiState.email,
        password = uiState.password,
        isInvalidCredentials = uiState.isInvalidCredentials,
        onEmailChange = onEmailChange,
        onPasswordChange = onPasswordChange,
        onForgotPasswordClick = onForgotPasswordClick,
        onSignUpClick = onSignUpClick,
        onSignInClick = onLogInClick)

    LoadingScreen(isLoading = uiState.isLoading)
}
