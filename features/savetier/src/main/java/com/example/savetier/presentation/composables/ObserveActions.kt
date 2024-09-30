package com.example.savetier.presentation.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.savetier.R
import com.example.savetier.presentation.model.SaveTierAction
import com.example.ui.view.composables.ShowToast

@Composable
internal fun ObserveActions(
    action: SaveTierAction,
) {
    when (action) {
        SaveTierAction.SaveSuccess -> {
            ShowToast(stringResource(R.string.tier_saved_successfully))
        }
        SaveTierAction.SaveError -> {
            ShowToast(stringResource(R.string.tier_saving_failed))
        }

        SaveTierAction.Initiate -> {}
    }
}