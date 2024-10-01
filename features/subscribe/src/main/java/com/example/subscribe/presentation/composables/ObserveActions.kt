package com.example.subscribe.presentation.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.subscribe.R
import com.example.subscribe.presentation.model.SubscribeAction
import com.example.ui.view.composables.ShowToast

@Composable
fun ObserveActions(action: SubscribeAction) {
    when (action) {
        SubscribeAction.Initial -> {}
        SubscribeAction.SubscribeSuccess -> {
            ShowToast(stringResource(R.string.subscription_successful))
        }
        SubscribeAction.SubscribeError -> {
            ShowToast(stringResource(R.string.subscription_failed))
        }
    }
}