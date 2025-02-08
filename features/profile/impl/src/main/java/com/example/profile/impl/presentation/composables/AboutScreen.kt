package com.example.profile.impl.presentation.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.profile.impl.R
import com.example.ui.themes.OnSurfaceTextAlpha

@Composable
internal fun AboutScreen(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text.ifEmpty { stringResource(R.string.no_description) },
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onBackground.copy(alpha = OnSurfaceTextAlpha),
        modifier = modifier
    )
}