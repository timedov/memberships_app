package com.example.subscribe.presentation.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.domain.model.UserDetailsModel
import com.example.ui.R
import com.example.ui.view.composables.AsyncImageCaching

@Composable
fun UserDetailsTitle(
    userDetails: UserDetailsModel
) {
    AsyncImageCaching(
        model = userDetails.imageUrl,
        contentDescription = null,
        placeholder = painterResource(id = R.drawable.empty_profile_pic),
        error = painterResource(id = R.drawable.empty_profile_pic),
        modifier = Modifier
            .size(120.dp)
            .clip(CircleShape)
    )
    Text(
        text = stringResource(com.example.subscribe.R.string.user_exclusive_content, userDetails.username),
        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}