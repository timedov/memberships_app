package com.example.savepost.impl.presentation.composables

import android.content.Intent
import android.os.Build
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.savepost.impl.R
import com.example.savepost.impl.presentation.model.SavePostAction
import com.example.savepost.impl.presentation.model.SavePostEvent
import com.example.ui.view.composable.ShowToast

@Composable
internal fun ObserveActions(
    action: SavePostAction,
    onRestoreDraftDialogConfirm: () -> Unit,
    onRestoreDraftDialogDismiss: () -> Unit
) {

    when (action) {
        SavePostAction.ShowRestoreDraftDialog -> {
            var showRestoreDialog by remember { mutableStateOf(true) }

            if (showRestoreDialog) {
                AlertDialog(
                    onDismissRequest = { showRestoreDialog = false },
                    confirmButton = {
                        TextButton(onClick = {
                            onRestoreDraftDialogConfirm()
                            showRestoreDialog = false
                        }) {
                            Text(stringResource(R.string.restore))
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = {
                            onRestoreDraftDialogDismiss()
                            showRestoreDialog = false
                        }) {
                            Text(stringResource(R.string.no))
                        }
                    },
                    title = { Text(stringResource(R.string.restore_post_draft_title)) },
                    text = { Text(stringResource(R.string.restore_post_draft_desc)) }
                )
            }
        }

        SavePostAction.SaveError ->
            ShowToast(stringResource(R.string.post_save_failed))

        SavePostAction.SaveSuccess -> {
            ShowToast(stringResource(R.string.saving_post))
        }

        SavePostAction.Initiate -> Unit
    }
}
