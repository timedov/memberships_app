package com.example.ui.view.composables

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.ui.R

@Composable
fun SaveAlertDialog(
    title: @Composable () -> Unit,
    text: @Composable () -> Unit,
    onShowDialogChange: (Boolean) -> Unit,
    onSaveClick: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { onShowDialogChange(false) },
        title = title,
        text = text,
        confirmButton = {
            TextButton(onClick = {
                onSaveClick()
                onShowDialogChange(false)
            }) {
                Text(text = stringResource(R.string.save))
            }
        },
        dismissButton = {
            TextButton(onClick = { onShowDialogChange(false) }) {
                Text(text = stringResource(R.string.cancel))
            }
        }
    )
}