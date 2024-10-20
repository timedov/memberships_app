package com.example.savepost.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.savepost.R
import com.example.ui.themes.Shapes

@Composable
fun DescriptionTextField(
    description: String,
    descriptionError: String,
    onDescriptionChange: (String) -> Unit,
    onImageIconClick: () -> Unit,
    onVideoIconClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.background(
                color = MaterialTheme.colorScheme.surfaceContainerHighest,
                shape = Shapes.large
            )
    ) {
        TextField(
            value = description,
            onValueChange = onDescriptionChange,
            placeholder = {
                Text(
                    text = stringResource(R.string.post_description_placeholder),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp),
            textStyle = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onSurface
            )
        )

        ContentToolbar(onImageIconClick = onImageIconClick, onVideoIconClick = onVideoIconClick)
    }
    Text(
        text = descriptionError,
        color = MaterialTheme.colorScheme.error,
        modifier = modifier
    )
}
