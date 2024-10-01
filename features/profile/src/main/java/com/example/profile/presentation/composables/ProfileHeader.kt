package com.example.profile.presentation.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ui.R
import com.example.ui.themes.OnSurfaceTextAlpha
import com.example.ui.themes.Shapes
import com.example.ui.view.composables.AsyncImageCaching

@Composable
fun ProfileHeader(
    name: String,
    imageUrl: String?,
    subscribers: String,
    joinedYear: Int,
    onSubscribeClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    AsyncImageCaching(
        model = imageUrl.orEmpty(),
        placeholder = painterResource(id = R.drawable.empty_profile_pic),
        error = painterResource(id = R.drawable.empty_profile_pic),
        contentDescription = stringResource(com.example.profile.R.string.profile_image),
        contentScale = ContentScale.Fit,
        modifier = modifier
            .padding(horizontal = 20.dp, vertical = 8.dp)
            .size(130.dp)
            .clip(CircleShape)
    )
    Text(
        text = name,
        style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
        modifier = modifier.padding(horizontal = 20.dp)
    )
    if (subscribers.isNotEmpty()) {
        Text(
            text = stringResource(com.example.profile.R.string.count_subscribers, subscribers),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = OnSurfaceTextAlpha),
            modifier = modifier.padding(vertical = 4.dp, horizontal = 20.dp)
        )
    }
    Text(
        text = stringResource(com.example.profile.R.string.joined_in_year, joinedYear),
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onBackground.copy(alpha = OnSurfaceTextAlpha),
        modifier = modifier.padding(vertical = 4.dp, horizontal = 20.dp)
    )
    Button(
        onClick = onSubscribeClick,
        colors = ButtonDefaults.filledTonalButtonColors(),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 12.dp),
        shape = Shapes.large,
    ) {
        Text(
            text = stringResource(com.example.profile.R.string.subscribe),
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
            modifier = modifier.padding(vertical = 4.dp)
        )
    }
}