package com.example.profile.presentation.composables

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.profile.R
import com.example.ui.themes.OnSurfaceTextAlpha

@Composable
fun AboutScreen(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text.ifEmpty { stringResource(R.string.no_description) },
        style = MaterialTheme.typography.bodyLarge,
        modifier = modifier
    )
    Text(
        text = stringResource(R.string.no_posts),
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onBackground.copy(alpha = OnSurfaceTextAlpha),
        modifier = modifier
    )
}