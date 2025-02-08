package com.example.signin.impl.presentation.composable

import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.example.signin.impl.presentation.SignInViewModel
import com.example.signin.impl.presentation.model.SignInEvent

@Composable
internal fun LoginScreen(viewModel: SignInViewModel) {
    val snackbarHostState = remember { SnackbarHostState() }
    val uiState by viewModel.uiState.collectAsState()


    ObserveActions(viewModel, snackbarHostState)

    ObserveState(
        uiState = uiState,
        onEmailChange = { viewModel.obtainEvent(SignInEvent.EmailChanged(it)) },
        onPasswordChange = { viewModel.obtainEvent(SignInEvent.PasswordChanged(it)) },
        onForgotPasswordClick = { viewModel.obtainEvent(SignInEvent.ForgotPasswordClick) },
        onSignUpClick = { viewModel.obtainEvent(SignInEvent.SignUpClick) },
        onLogInClick = { viewModel.obtainEvent(SignInEvent.SignInClick) }
    )

    SnackbarHost(hostState = snackbarHostState)
}