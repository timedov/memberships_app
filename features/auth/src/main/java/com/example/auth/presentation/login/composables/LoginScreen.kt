package com.example.auth.presentation.login.composables

import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.example.auth.presentation.login.LoginViewModel
import com.example.auth.presentation.login.model.LoginEvent

@Composable
fun LoginScreen(viewModel: LoginViewModel) {
    val snackbarHostState = remember { SnackbarHostState() }
    val uiState by viewModel.uiState.collectAsState()


    ObserveActions(viewModel, snackbarHostState)

    ObserveState(
        uiState = uiState,
        onEmailChange = { viewModel.obtainEvent(LoginEvent.EmailChanged(it)) },
        onPasswordChange = { viewModel.obtainEvent(LoginEvent.PasswordChanged(it)) },
        onForgotPasswordClick = { viewModel.obtainEvent(LoginEvent.ForgotPasswordClick) },
        onSignUpClick = { viewModel.obtainEvent(LoginEvent.SignUpClick) },
        onLogInClick = { viewModel.obtainEvent(LoginEvent.LogInClick) }
    )

    SnackbarHost(hostState = snackbarHostState)
}