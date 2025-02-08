package com.example.signin.impl.presentation.composable

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.signin.impl.presentation.SignInViewModel
import com.example.signin.impl.presentation.model.LoginAction

@Composable
internal fun ObserveActions(
    viewModel: SignInViewModel,
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