package com.example.savetier.presentation.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import com.example.ui.themes.Shapes

@Composable
fun FormTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String = "",
    error: String?,
    keyboardType: KeyboardType = KeyboardType.Text,
    maxLines: Int = 1,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = { onValueChange(it) },
        label = { Text(label) },
        isError = error != null,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
        maxLines = maxLines,
        shape = Shapes.large,
        colors = TextFieldDefaults.colors(
            disabledIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Red,
            unfocusedIndicatorColor = Color.Transparent
        ),
        modifier = modifier
    )
    if (error != null) {
        Text(text = error, color = MaterialTheme.colorScheme.error, modifier = modifier)
    }
}
