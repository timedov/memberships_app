package com.example.auth.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.auth.R
import com.example.auth.presentation.login.model.LoginAction
import com.example.auth.presentation.login.model.LoginEvent
import com.example.auth.presentation.login.model.LoginState
import com.example.ui.themes.OnSurfaceTextAlpha
import com.example.ui.view.composables.ShowLoading
import com.example.ui.view.composables.TextFieldWithIcon

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
        CredentialsAndLogIn(viewModel, isInvalidCredentials)
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = stringResource(R.string.suggest_sign_up),
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
    ShowLoading(true)
}

@Composable
private fun TitleText() {
    Spacer(modifier = Modifier.height(20.dp))
    Text(
        text = stringResource(id = R.string.login),
        style = MaterialTheme.typography.headlineSmall.copy(
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold
        ),
        modifier = Modifier.padding(vertical = 16.dp)
    )
    Text(
        text = stringResource(R.string.welcome_back),
        style = MaterialTheme.typography.headlineMedium.copy(
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold
        ),
        modifier = Modifier.padding(vertical = 16.dp)
    )
    Text(
        text = stringResource(R.string.login_desc),
        style = MaterialTheme.typography.bodyLarge.copy(
            color = MaterialTheme.colorScheme.onBackground
        ),
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(bottom = 24.dp)
    )
}

@Composable
private fun CredentialsAndLogIn(
    viewModel: LoginViewModel,
    isInvalidCredentials: Boolean
) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    TextFieldWithIcon(
        value = email,
        onValueChange = { email = it },
        label = stringResource(R.string.email),
        modifier = Modifier
            .fillMaxWidth(),
        isError = isInvalidCredentials,
    )
    Spacer(modifier = Modifier.height(16.dp))
    TextFieldWithIcon(
        value = password,
        onValueChange = { password = it },
        label = stringResource(R.string.password),
        modifier = Modifier.fillMaxWidth(),
        isPassword = true,
        isError = isInvalidCredentials
    )
    Spacer(modifier = Modifier.height(36.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(R.string.forgot_password),
            style = MaterialTheme.typography.bodyLarge.copy(
                color = MaterialTheme
                    .colorScheme
                    .onBackground,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.clickable {
                viewModel.obtainEvent(LoginEvent.ForgotPasswordClick)
            }
        )
        Text(
            text = stringResource(R.string.log_in),
            style = MaterialTheme.typography.bodyLarge.copy(
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.clickable {
                viewModel.obtainEvent(LoginEvent.LogInClick(email, password))
            }
        )
    }
}

@Composable
private fun ObserveActions(
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

@Composable
private fun ObserveState(
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
