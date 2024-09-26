package com.example.subscribe.presentation.composables

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.subscribe.R
import com.example.subscribe.presentation.model.SubscribeAction

@Composable
fun ObserveActions(action: SubscribeAction?) {
    when (action) {
        SubscribeAction.SubscribeSuccess -> {
            Toast
                .makeText(
                    LocalContext.current,
                    stringResource(id = R.string.subscription_successful),
                    Toast.LENGTH_SHORT
                )
                .show()
        }
        SubscribeAction.SubscribeError -> {
            Toast
                .makeText(
                    LocalContext.current,
                    stringResource(R.string.subscription_failed),
                    Toast.LENGTH_SHORT
                )
                .show()
        }
        else -> {}
    }
}