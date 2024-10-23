package com.example.savepost.presentation.composables

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.media3.common.Player
import com.example.domain.model.ContentType
import com.example.savepost.R
import com.example.ui.themes.Shapes
import com.example.ui.view.composables.AsyncImageCaching
import com.example.ui.view.composables.FormTextField
import com.example.ui.view.composables.VideoPlayer

@Composable
fun PostForm(
    title: String,
    titleError: String,
    content: Uri,
    contentType: ContentType,
    description: String,
    descriptionError: String,
    player: Player,
    onTitleChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onRemoveClick: () -> Unit,
    onImageIconClick: () -> Unit,
    onVideoIconClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    val scrollState = rememberScrollState()

    Column(
        modifier = modifier.verticalScroll(scrollState)
    ) {
        FormTextField(
            value = title,
            onValueChange = onTitleChange,
            label = { Text(text = stringResource(id = R.string.post_title_placeholder)) },
            error = titleError,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp)
        )

        if (content != Uri.EMPTY) {
            ContentItem(
                onRemoveClick = onRemoveClick,
                modifier = Modifier.fillMaxWidth().padding(4.dp)
            ) {
                when (contentType) {
                    ContentType.IMAGE ->
                        AsyncImageCaching(
                            model = content,
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp)
                                .clip(Shapes.large)
                        )
                    ContentType.VIDEO ->
                        VideoPlayer(
                            player = player,
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(16 / 9f)

                        )
                    else -> Unit
                }
            }
        }

        DescriptionTextField(
            description = description,
            descriptionError = descriptionError,
            onDescriptionChange = onDescriptionChange,
            onImageIconClick = onImageIconClick,
            onVideoIconClick = onVideoIconClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        )
    }
}
