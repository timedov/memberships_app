package com.example.savetier.presentation.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.savetier.R
import com.example.savetier.presentation.SaveTierViewModel
import com.example.savetier.presentation.model.SaveTierAction
import com.example.savetier.presentation.model.SaveTierEvent
import com.example.savetier.presentation.model.SaveTierState
import com.example.ui.view.composables.CenterAlignedTopAppBarWithBackButton
import com.example.ui.view.composables.ErrorScreen
import com.example.ui.view.composables.LoadingScreen

@Composable
fun SaveTierScreen(viewModel: SaveTierViewModel) {
    val state by viewModel.uiState.collectAsState()
    val action by viewModel.actionsFlow.collectAsState(SaveTierAction.Initiate)

    var showDialog by remember { mutableStateOf(false) }

    ObserveActions(action = action)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBarWithBackButton(
                title = stringResource(R.string.save_tier),
                onBackClick = { viewModel.obtainEvent(SaveTierEvent.BackClick) }
            )
        },
    ) { paddingValues ->
        when (state) {
            is SaveTierState.Form -> {
                val formState = state as SaveTierState.Form

                TierForm(
                    name = formState.name,
                    nameError = formState.nameError,
                    price = formState.price,
                    priceError = formState.priceError,
                    description = formState.description,
                    descriptionError = formState.descriptionError,
                    onNameChange = { viewModel.obtainEvent(SaveTierEvent.NameChange(it)) },
                    onPriceChange = { viewModel.obtainEvent(SaveTierEvent.PriceChange(it)) },
                    onDescriptionChange = { viewModel.obtainEvent(SaveTierEvent.DescriptionChange(it)) },
                    onSaveClick = { showDialog = true },
                    modifier = Modifier.fillMaxSize().padding(paddingValues)
                )

                if (showDialog) {
                    AlertDialog(
                        onDismissRequest = { showDialog = false },
                        title = {
                            Text(text = stringResource(R.string.save_tier))
                        },
                        text = {
                            Text(text = stringResource(R.string.confirm_saving_tier))
                        },
                        confirmButton = {
                            TextButton(onClick = {
                                viewModel.obtainEvent(SaveTierEvent.SaveTier)
                                showDialog = false
                            }) {
                                Text(text = stringResource(R.string.save))
                            }
                        },
                        dismissButton = {
                            TextButton(onClick = { showDialog = false }) {
                                Text(text = stringResource(R.string.cancel))
                            }
                        }
                    )
                }
            }

            is SaveTierState.Error -> {
                ErrorScreen(onRetryClick = { viewModel.obtainEvent(SaveTierEvent.RetryClick) })
            }

            SaveTierState.Loading -> {
                LoadingScreen(isLoading = true)
            }
        }
    }
}
