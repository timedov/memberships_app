package com.example.profile.impl.presentation.composables

import android.util.Base64
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
import com.example.ui.view.composable.AsyncImageCaching
import com.example.ui.R as UiR

@Composable
internal fun PostItem(
    post: PostUiModel,
    onItemClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onItemClick(post.id) }
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
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
        if (post.content.isNotEmpty()) {
            AsyncImageCaching(
                model = Base64.decode(post.content, Base64.DEFAULT),
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
}
