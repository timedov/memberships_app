package com.example.postdetails.presentation.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Comment
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.ui.view.composables.IconWithText

@Composable
fun PostToolbar(
    favoriteCount: String,
    commentsCount: String,
    isFavorite: Boolean,
    onFavoriteClick: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconWithText(
            imageVector = if (isFavorite) Icons.Default.FavoriteBorder else Icons.Default.Favorite,
            contentDescription = null,
            text = favoriteCount,
            modifier = Modifier.clickable { onFavoriteClick(!isFavorite) }
        )
        IconWithText(
            imageVector = Icons.AutoMirrored.Outlined.Comment,
            contentDescription = null,
            text = commentsCount,
        )
    }
}
