package com.example.profile.presentation.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ui.model.PostUiModel
import com.example.ui.themes.OnSurfaceTextAlpha
import com.example.ui.themes.Shapes
import com.example.ui.view.composables.AsyncImageCaching
import com.example.ui.R as UiR

@Composable
fun PostItem(post: PostUiModel, onClick: (Long) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(post.id) }
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = post.postedAgo,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = OnSurfaceTextAlpha)
            )
            Text(
                text = post.title,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(top = 4.dp, bottom = 4.dp, end = 4.dp)
            )
            Text(
                text = post.author,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = OnSurfaceTextAlpha)
            )
        }
        AsyncImageCaching(
            model = post.image,
            contentDescription = null,
            placeholder = painterResource(id = UiR.drawable.no_image),
            error = painterResource(id = UiR.drawable.no_image),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .sizeIn(maxHeight = 80.dp)
                .aspectRatio(16f / 9f)
                .clip(Shapes.large)

        )
    }
}
