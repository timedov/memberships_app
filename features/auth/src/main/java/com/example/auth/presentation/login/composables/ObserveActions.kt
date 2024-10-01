package com.example.auth.presentation.login.composables

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.auth.presentation.login.LoginViewModel
import com.example.auth.presentation.login.model.LoginAction

@Composable
fun ObserveActions(
    viewModel: LoginViewModel,
    snackbarHostState: SnackbarHostState,
) {
    LaunchedEffect(Unit) {
        viewModel.actionsFlow.collect { action ->
            when (action) {
                is LoginAction.ShowMessage ->
                    snackbarHostState.showSnackbar(message = action.message)
            }
        }
    }
}