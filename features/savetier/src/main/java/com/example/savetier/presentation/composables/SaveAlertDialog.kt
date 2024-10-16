package com.example.savetier.presentation.composables

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.savetier.R

@Composable
fun SaveAlertDialog(
    onShowDialogChange: (Boolean) -> Unit,
    onSaveClick: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { onShowDialogChange(false) },
        title = {
            Text(text = stringResource(R.string.save_tier))
        },
        text = {
            Text(text = stringResource(R.string.confirm_saving_tier))
        },
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