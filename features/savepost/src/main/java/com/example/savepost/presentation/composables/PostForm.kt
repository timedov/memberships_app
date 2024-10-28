package com.example.savepost.presentation.composables

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
import androidx.media3.common.Player
import com.example.domain.model.ContentType
import com.example.savepost.R
import com.example.ui.view.composables.FormTextField

@Composable
fun PostForm(
    title: String,
    titleError: String,
    content: String,
    contentType: ContentType,
    description: String,
    descriptionError: String,
    player: Player,
    requiresSubscription: Boolean,
    onTitleChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onRemoveClick: () -> Unit,
    onImageIconClick: () -> Unit,
    onVideoIconClick: () -> Unit,
    onRequireSubscriptionChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {

    val scrollState = rememberScrollState()

    Column(
        modifier = modifier.verticalScroll(scrollState).padding(horizontal = 4.dp)
    ) {
        FormTextField(
            value = title,
            onValueChange = onTitleChange,
            label = { Text(text = stringResource(id = R.string.post_title_placeholder)) },
            error = titleError,
            modifier = Modifier
                .fillMaxWidth()
        )

        if (content.isNotEmpty()) {
            ContentItem(
                onRemoveClick = onRemoveClick,
                player = player,
                content = content,
                contentType = contentType,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }

        DescriptionTextField(
            description = description,
            descriptionError = descriptionError,
            onDescriptionChange = onDescriptionChange,
            onImageIconClick = onImageIconClick,
            onVideoIconClick = onVideoIconClick,
            modifier = Modifier
                .fillMaxWidth()
        )

        RequireSubscriptionCheckbox(
            requiresSubscription = requiresSubscription,
            onRequireSubscriptionChange = onRequireSubscriptionChange,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
