package com.example.feed.impl.presentation.composable

import android.util.Base64
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ui.model.PostUiModel
import com.example.ui.themes.GradientHeight
import com.example.ui.themes.MaxPostItemHeight
import com.example.ui.themes.Shapes
import com.example.ui.themes.SurfaceGradientEndAlpha
import com.example.ui.themes.SurfaceGradientStartAlpha
import com.example.ui.view.composable.AsyncImageCaching
import com.example.ui.R as UiR

@Composable
fun PostItem(
    post: PostUiModel,
    onPostClick: (String) -> Unit,
    onProfileClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var contentHeight by remember { mutableFloatStateOf(0f) }
    val maxHeightPx = with(LocalDensity.current) { MaxPostItemHeight.toPx() }
    val showGradient by remember(contentHeight) { derivedStateOf { contentHeight >= maxHeightPx } }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .heightIn(max = MaxPostItemHeight)
            .clickable { onPostClick(post.id) }
            .background(MaterialTheme.colorScheme.surface, shape = Shapes.medium)
            .onGloballyPositioned { contentHeight = it.size.height.toFloat() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            ProfileHeader(
                profileImage = post.profileImage,
                author = post.author,
                postedAgo = post.postedAgo,
                onProfileClick = onProfileClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )

            Text(
                text = post.title,
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )

            post.content.takeIf(String::isNotEmpty)?.let {
                AsyncImageCaching(
                    model = Base64.decode(it, Base64.DEFAULT),
                    error = painterResource(id = UiR.drawable.no_image),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                        .clip(Shapes.large)
                        .padding(vertical = 4.dp),
                    contentScale = ContentScale.Crop
                )
            }

            post.body.takeIf(String::isNotEmpty)?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                )
            }
        }

        if (showGradient) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(GradientHeight)
                    .align(Alignment.BottomCenter)
                    .background(
                        brush = Brush.verticalGradient(
                            listOf(
                                MaterialTheme.colorScheme.surface
                                    .copy(alpha = SurfaceGradientStartAlpha),
                                MaterialTheme.colorScheme.surface
                                    .copy(alpha = SurfaceGradientEndAlpha),
                                MaterialTheme.colorScheme.surface
                            )
                        ),
                        shape = Shapes.medium
                    )
            )
        }
    }
}




