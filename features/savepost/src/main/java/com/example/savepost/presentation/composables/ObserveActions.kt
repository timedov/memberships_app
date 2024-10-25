package com.example.savepost.presentation.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.savepost.R
import com.example.savepost.presentation.model.SavePostAction
import com.example.ui.view.composables.ShowToast

@Composable
fun ObserveActions(action: SavePostAction) {

    when (action) {
        SavePostAction.SaveError ->
            ShowToast(stringResource(R.string.post_saving_failed))

        SavePostAction.Initiate -> Unit
    }
}
