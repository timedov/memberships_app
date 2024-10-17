package com.example.auth.presentation.login.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.auth.R
import com.example.ui.view.composables.TextFieldWithIcon

@Composable
fun CredentialsAndLogIn(
    email: String,
    password: String,
    isInvalidCredentials: Boolean,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onForgotPasswordClick: () -> Unit,
    onLogInClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    TextFieldWithIcon(
        value = email,
        onValueChange = onEmailChange,
        label = stringResource(R.string.email),
        modifier = modifier
            .fillMaxWidth(),
        isError = isInvalidCredentials,
    )
    Spacer(modifier = modifier.height(16.dp))
    TextFieldWithIcon(
        value = password,
        onValueChange = onPasswordChange,
        label = stringResource(R.string.password),
        modifier = modifier.fillMaxWidth(),
        isPassword = true,
        isError = isInvalidCredentials
    )
    Spacer(modifier = modifier.height(36.dp))
    Row(
        modifier = modifier
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
            modifier = modifier.clickable { onForgotPasswordClick() }
        )
        Text(
            text = stringResource(R.string.log_in),
            style = MaterialTheme.typography.bodyLarge.copy(
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold
            ),
            modifier = modifier.clickable { onLogInClick() }
        )
    }
}