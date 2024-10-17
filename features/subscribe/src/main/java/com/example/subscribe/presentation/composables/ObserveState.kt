package com.example.subscribe.presentation.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.subscribe.R
import com.example.subscribe.presentation.model.SubscribeState
import com.example.ui.view.composables.ErrorScreen
import com.example.ui.view.composables.LoadingScreen

@Composable
fun ObserveState(
    state: SubscribeState,
    onSubscribeClick: () -> Unit,
    onTierSelected: (Long) -> Unit,
    onRetryClick: () -> Unit,
) {
    if (state.isError) {
        ErrorScreen(onRetryClick = onRetryClick)
    } else {
        SubscribeContent(
            userDetails = state.userDetails,
            tiers = state.tiers,
            selectedTierId = state.selectedTierId,
            isSubscribeButtonEnabled =
            if (state.isCurrentUser) true else state.selectedTierId != -1L,
            subscribeButtonTitle = stringResource(
                id = if (state.isCurrentUser) R.string.plus else R.string.subscribe
            ),
            onTierSelected = onTierSelected,
            onSubscribeClick = onSubscribeClick
        )

        LoadingScreen(isLoading = state.isLoading)
    }
}
