package com.example.postdetails.presentation.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.postdetails.R
import com.example.ui.themes.Shapes

@Composable
fun CommentTextField(
    onCommentSend: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    var commentValue by remember { mutableStateOf("") }

    TextField(
        value = commentValue,
        onValueChange = { commentValue = it },
        label = { Text(stringResource(R.string.comment)) },
        trailingIcon = {
            IconButton(
                onClick = { onCommentSend(commentValue) },
                enabled = commentValue.isNotEmpty()
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.Send,
                    contentDescription = null
                )
            }
        },
        shape = Shapes.large,
        colors = TextFieldDefaults.colors(
            disabledIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Red,
            unfocusedIndicatorColor = Color.Transparent
        ),
        modifier = modifier
    )
}