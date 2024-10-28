package com.example.profile.presentation.composables

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.profile.R

@Composable
fun AboutScreen(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text.ifEmpty { stringResource(R.string.no_description) },
        style = MaterialTheme.typography.bodyLarge,
        modifier = modifier
    )
}