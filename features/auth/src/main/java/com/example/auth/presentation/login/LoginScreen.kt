package com.example.auth.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.auth.R
import com.example.auth.presentation.login.model.LoginAction
import com.example.auth.presentation.login.model.LoginEvent
import com.example.auth.presentation.login.model.LoginState
import com.example.ui.themes.OnSurfaceTextAlpha
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(viewModel: LoginViewModel) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val uiState by viewModel.uiState.collectAsState()

    if (uiState is LoginState.Loading) {
        ShowLoading()
    }

    CollectActions(viewModel, snackbarHostState, scope)

    SnackbarHost(hostState = snackbarHostState)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp)
    ) {
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

        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        TextFieldWithIcon(
            value = email,
            onValueChange = { email = it },
            label = stringResource(R.string.email),
            modifier = Modifier
                .fillMaxWidth(),
            isError = uiState is LoginState.NotValidEmail
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextFieldWithIcon(
            value = password,
            onValueChange = { password = it },
            label = stringResource(R.string.password),
            modifier = Modifier.fillMaxWidth(),
            isPassword = true
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
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = stringResource(R.string.suggest_sign_up),
            style = MaterialTheme.typography.bodyLarge.copy(
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = OnSurfaceTextAlpha)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .clickable { },
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
}

@Composable
private fun CollectActions(
    viewModel: LoginViewModel,
    snackbarHostState: SnackbarHostState,
    scope: CoroutineScope
) {
    LaunchedEffect(Unit) {
        viewModel.actionsFlow.collect { action ->
            when (action) {
                is LoginAction.NavigateToHomeScreen -> {
                    viewModel.navigateToHomeScreen()
                }

                is LoginAction.NavigateToForgotPasswordScreen -> {}
                is LoginAction.ShowMessage -> scope.launch {
                    snackbarHostState.showSnackbar(message = action.message)
                }
            }
        }
    }
}

@Composable
private fun ShowLoading() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f)),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun TextFieldWithIcon(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    isPassword: Boolean = false,
    isError: Boolean = false
) {
    val passwordVisibility by remember { mutableStateOf(false) }
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        isError = isError,
        trailingIcon = {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = stringResource(R.string.arrow),
                modifier = Modifier
                    .size(32.dp)
                    .padding(4.dp)
            )
        },
        shape = RoundedCornerShape(12.dp),
        colors = TextFieldDefaults.colors(
            disabledIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Red,
            unfocusedIndicatorColor = Color.Transparent
        ),
        visualTransformation =
        if (isPassword && !passwordVisibility) PasswordVisualTransformation()
        else VisualTransformation.None,
        modifier = modifier
    )
}
