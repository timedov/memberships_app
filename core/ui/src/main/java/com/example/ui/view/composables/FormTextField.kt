package com.example.ui.view.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.ui.themes.Shapes

@Composable
fun FormTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: @Composable () -> Unit,
    error: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    maxLines: Int = 1,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = label,
        isError = error.isNotEmpty(),
        keyboardOptions = keyboardOptions,
        maxLines = maxLines,
        shape = Shapes.large,
        colors = TextFieldDefaults.colors(
            disabledIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        modifier = modifier
    )
    Text(text = error, color = MaterialTheme.colorScheme.error, modifier = modifier)
}
