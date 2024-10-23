package com.example.savepost.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.common.utils.Constants
import com.example.ui.view.composables.TextLengthCounter

@Composable
fun ContentToolbar(
    textLength: Int,
    onImageIconClick: () -> Unit,
    onVideoIconClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Start
    ) {
        IconButton(onClick = onImageIconClick) {
            Icon(
                imageVector = Icons.Default.Image,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(24.dp)
            )
        }

        IconButton(onClick = onVideoIconClick) {
            Icon(
                imageVector = Icons.Default.Videocam,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(24.dp)
            )
        }

        TextLengthCounter(
            textLength = textLength,
            maxLength = Constants.MAX_POST_DESCRIPTION_LENGTH,
            textColor =
            if (textLength > Constants.MAX_POST_DESCRIPTION_LENGTH) MaterialTheme.colorScheme.error
            else MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp, horizontal = 12.dp),
            horizontalArrangement = Arrangement.End
        )
    }
}