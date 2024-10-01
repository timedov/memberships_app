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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.auth.R
import com.example.ui.view.composables.TextFieldWithIcon

@Composable
fun CredentialsAndLogIn(
    isInvalidCredentials: Boolean,
    onForgotPasswordClick: () -> Unit,
    onSignInClick: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    TextFieldWithIcon(
        value = email,
        onValueChange = { email = it },
        label = stringResource(R.string.email),
        modifier = modifier
            .fillMaxWidth(),
        isError = isInvalidCredentials,
    )
    Spacer(modifier = modifier.height(16.dp))
    TextFieldWithIcon(
        value = password,
        onValueChange = { password = it },
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
            modifier = modifier.clickable {  onSignInClick(email, password) }
        )
    }
}