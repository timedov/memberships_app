package com.example.auth.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.auth.R
import com.example.auth.presentation.login.composables.CredentialsAndLogIn
import com.example.auth.presentation.login.composables.ObserveActions
import com.example.auth.presentation.login.composables.ObserveState
import com.example.auth.presentation.login.composables.TitleText
import com.example.auth.presentation.login.model.LoginEvent
import com.example.ui.themes.OnSurfaceTextAlpha
import com.example.ui.view.composables.ShowLoading

@Composable
fun LoginScreen(viewModel: LoginViewModel) {
    val snackbarHostState = remember { SnackbarHostState() }
    val uiState by viewModel.uiState.collectAsState()

    var isInvalidCredentials by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }

    ObserveActions(viewModel, snackbarHostState)
    ObserveState(
        uiState = uiState,
        snackbarHostState = snackbarHostState,
        isInvalidCredentialsSetter = { isInvalidCredentials = it },
        isLoadingSetter = { isLoading = it }
        )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp)
    ) {
        TitleText()
        CredentialsAndLogIn(
            onForgotPasswordClick = { viewModel.obtainEvent(LoginEvent.ForgotPasswordClick) },
            onSignInClick = { email, password ->
                viewModel.obtainEvent(LoginEvent.LogInClick(email, password)) },
            isInvalidCredentials = isInvalidCredentials,
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = buildAnnotatedString {
                append(stringResource(R.string.don_t_have_an_account))

                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = MaterialTheme.typography.bodyMedium.fontWeight
                    )
                ) {
                    append(stringResource(R.string.sign_up))
                }
            },
            style = MaterialTheme.typography.bodyLarge.copy(
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = OnSurfaceTextAlpha)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .clickable { viewModel.obtainEvent(LoginEvent.SignUpClick) },
        )
        Spacer(modifier = Modifier.fillMaxHeight(fraction = 0.74f))
        Text(
            text = stringResource(R.string.terms_of_use_text),
            style = MaterialTheme.typography.bodyLarge.copy(
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = OnSurfaceTextAlpha)
            ),
            modifier = Modifier
                .padding(bottom = 24.dp),
        )
    }

    SnackbarHost(hostState = snackbarHostState)
    ShowLoading(isLoading = true)
}