package com.example.postdetails.presentation.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ui.R
import com.example.ui.model.CommentUiModel
import com.example.ui.view.composables.AsyncImageCaching

@Composable
fun CommentItem(
    comment: CommentUiModel,
    onProfileClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImageCaching(
            model = comment.profileImageUrl,
            contentDescription = null,
            placeholder = painterResource(id = R.drawable.empty_profile_pic),
            error = painterResource(id = R.drawable.empty_profile_pic),
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .clickable { onProfileClick(comment.username) }
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = comment.username,
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                    color = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = comment.postedWhen,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = comment.body,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White
            )
        }
    }
}
