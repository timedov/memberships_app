package com.example.postdetails.presentation.composables

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.postdetails.R

@Composable
fun SubscriptionRequiredDialog(
    onDismiss: () -> Unit,
    onSubscribeClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = {
            Text(
                text = stringResource(R.string.subscription_required),
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center
            )
        },
        text = {
            Text(
                text = stringResource(id = R.string.requires_subscription),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )
        },
        confirmButton = {
            TextButton(
                onClick = { onSubscribeClick() },
            ) {
                Text(text = stringResource(id = R.string.subscribe))
            }
        },
        dismissButton = {
            TextButton(
                onClick = { onDismiss() },
            ) {
                Text(text = stringResource(id = R.string.leave))
            }
        },
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
    )
}


