package com.example.savepost.impl.presentation.composables

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
import com.example.savepost.impl.R
import com.example.ui.themes.Shapes

@Composable
internal fun DescriptionTextField(
    description: String,
    descriptionError: String,
    onDescriptionChange: (String) -> Unit,
    onImageIconClick: () -> Unit,
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
            label = {
                Text(
                    text = stringResource(R.string.post_description),
                )
            },
            isError = descriptionError.isNotEmpty(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp),
            textStyle = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onSurface
            )
        )

        ContentToolbar(
            textLength = description.length,
            onImageIconClick = onImageIconClick,
            modifier = Modifier.fillMaxWidth()
        )
    }
    if (descriptionError.isNotEmpty())
        Text(
            text = descriptionError,
            color = MaterialTheme.colorScheme.error,
            modifier = modifier
        )
}
