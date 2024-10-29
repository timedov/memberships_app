package com.example.savepost.presentation.composables

import android.content.Intent
import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.savepost.R
import com.example.savepost.presentation.model.SavePostAction
import com.example.ui.view.composables.ShowToast

@Composable
fun ObserveActions(action: SavePostAction) {

    when (action) {
        SavePostAction.SaveError ->
            ShowToast(stringResource(R.string.post_saving_failed))

        SavePostAction.SaveSuccess -> {
            ShowToast(stringResource(R.string.post_saved_successfully))
            LocalContext.current.apply {
                val mIntent = Intent(
                    this,
                    Class.forName("com.example.uploadpost.service.UploadPostService")
                )

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) startForegroundService(mIntent)
                else startService(mIntent)
            }
        }

        SavePostAction.Initiate -> Unit
    }
}
