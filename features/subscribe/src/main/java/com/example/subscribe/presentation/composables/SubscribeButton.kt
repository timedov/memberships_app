package com.example.subscribe.presentation.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun SubscribeButton(
    onButtonClick: () -> Unit,
    isEnabled: Boolean,
    buttonTitle: String,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onButtonClick,
        enabled = isEnabled,
        colors = ButtonDefaults.filledTonalButtonColors(),
        modifier = modifier,
        shape = MaterialTheme.shapes.large
    ) {
        Text(
            text = buttonTitle,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(vertical = 8.dp),
        )
    }
}