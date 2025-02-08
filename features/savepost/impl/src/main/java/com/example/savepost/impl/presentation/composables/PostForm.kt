package com.example.savepost.impl.presentation.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.savepost.impl.R
import com.example.ui.view.composable.FormTextField

@Composable
internal fun PostForm(
    title: String,
    titleError: String,
    content: String,
    description: String,
    descriptionError: String,
    requiresSubscription: Boolean,
    onTitleChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onRemoveClick: () -> Unit,
    onImageIconClick: () -> Unit,
    onRequireSubscriptionChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {

    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .verticalScroll(scrollState)
            .padding(horizontal = 4.dp)
    ) {
        FormTextField(
            value = title,
            onValueChange = onTitleChange,
            label = { Text(text = stringResource(R.string.title)) },
            error = titleError,
            modifier = Modifier
                .fillMaxWidth()
        )

        if (content.isNotEmpty()) {
            ContentItem(
                onRemoveClick = onRemoveClick,
                content = content,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }

        DescriptionTextField(
            description = description,
            descriptionError = descriptionError,
            onDescriptionChange = onDescriptionChange,
            onImageIconClick = onImageIconClick,
            modifier = Modifier.fillMaxWidth()
        )

        RequireSubscriptionCheckbox(
            requiresSubscription = requiresSubscription,
            onRequireSubscriptionChange = onRequireSubscriptionChange,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
