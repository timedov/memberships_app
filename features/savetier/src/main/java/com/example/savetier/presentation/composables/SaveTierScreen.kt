package com.example.savetier.presentation.composables

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import com.example.savetier.R
import com.example.savetier.presentation.SaveTierViewModel
import com.example.savetier.presentation.model.SaveTierAction
import com.example.savetier.presentation.model.SaveTierEvent
import com.example.ui.view.composables.CenterAlignedTopAppBarWithBackButton

@Composable
fun SaveTierScreen(viewModel: SaveTierViewModel) {
    val state by viewModel.uiState.collectAsState()
    val action by viewModel.actionsFlow.collectAsState(SaveTierAction.Initiate)

    ObserveActions(action = action)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBarWithBackButton(
                title = stringResource(R.string.save_tier),
                onBackClick = { viewModel.obtainEvent(SaveTierEvent.BackClick) }
            )
        },
    ) { paddingValues ->
        ObserveState(
            state = state,
            paddingValues = paddingValues,
            onNameChange = { viewModel.obtainEvent(SaveTierEvent.NameChange(it)) },
            onPriceChange = { viewModel.obtainEvent(SaveTierEvent.PriceChange(it.toDouble())) },
            onDescriptionChange = { viewModel.obtainEvent(SaveTierEvent.DescriptionChange(it)) },
            onSaveClick = { viewModel.obtainEvent(SaveTierEvent.SaveTier) },
            onRetryClick = { viewModel.obtainEvent(SaveTierEvent.RetryClick) }
        )
    }
}