package com.example.savepost.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.savepost.R
import com.example.ui.view.composables.FormTextField

@Composable
fun PostForm(
    title: String,
    titleError: String,
    description: String,
    descriptionError: String,
    onTitleChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onImageIconClick: () -> Unit,
    onVideoIconClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(modifier = modifier) {
        FormTextField(
            value = title,
            onValueChange = onTitleChange,
            label = { Text(text = stringResource(id = R.string.post_title_placeholder)) },
            error = titleError,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp)
        )

        DescriptionTextField(
            description = description,
            descriptionError = descriptionError,
            onDescriptionChange = onDescriptionChange,
            onImageIconClick = onImageIconClick,
            onVideoIconClick = onVideoIconClick,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth().padding(4.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = stringResource(R.string.description_length_counter, description.length),
                color = if (description.length > 500) MaterialTheme.colorScheme.error
                else Color.Gray
            )
        }
    }
}
