package com.example.savepost.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.media3.common.Player
import com.example.domain.model.ContentType
import com.example.ui.themes.Shapes
import com.example.ui.view.composables.AsyncImageCaching
import com.example.ui.view.composables.VideoPlayer

@Composable
fun ContentItem(
    onRemoveClick: () -> Unit,
    content: String,
    player: Player,
    contentType: ContentType,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        when (contentType) {
            ContentType.IMAGE ->
                AsyncImageCaching(
                    model = content,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
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

        IconButton(
            onClick = onRemoveClick,
            modifier = Modifier
                .align(Alignment.TopEnd)

        ) {
            Icon(
                imageVector = Icons.Default.Cancel,
                contentDescription = null,
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                        shape = CircleShape,
                    )
                    .padding(4.dp)
            )
        }
    }
}