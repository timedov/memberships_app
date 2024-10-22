package com.example.ui.view.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.ui.R
import com.example.ui.model.CommentUiModel

@Composable
fun CommentItem(
    comment: CommentUiModel,
    onProfileClick: (String) -> Unit,
    onReplyClick: (String) -> Unit,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
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

        CommentBody(
            username = comment.username,
            postedWhen = comment.postedWhen,
            text = comment.body.text,
            onReplyClick = { onReplyClick(comment.id) }
        )
    }
}
