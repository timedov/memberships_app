package com.example.auth.presentation.login.composables

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.auth.presentation.login.model.LoginState

@Composable
fun ObserveState(
    uiState: LoginState,
    snackbarHostState: SnackbarHostState,
    isInvalidCredentialsSetter: (Boolean) -> Unit,
    isLoadingSetter: (Boolean) -> Unit
) {
    LaunchedEffect(uiState) {
        when (uiState) {
            is LoginState.Initial -> {
                isInvalidCredentialsSetter(false)
                isLoadingSetter(false)
            }
            is LoginState.InvalidCredentials -> {
                isInvalidCredentialsSetter(true)
                isLoadingSetter(false)
            }
            is LoginState.Loading -> {
                isInvalidCredentialsSetter(false)
                isLoadingSetter(true)
            }
            is LoginState.Error -> {
                isInvalidCredentialsSetter(false)
                isLoadingSetter(false)
                snackbarHostState.showSnackbar(uiState.error.message.toString())
            }
        }
    }
}
