package com.example.savetier.presentation.composables

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.savetier.R
import com.example.savetier.presentation.model.SaveTierState
import com.example.ui.view.composables.ErrorScreen
import com.example.ui.view.composables.LoadingScreen

@Composable
fun ObserveState(
    state: SaveTierState,
    paddingValues: PaddingValues,
    onNameChange: (String) -> Unit,
    onPriceChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onSaveClick: () -> Unit,
    onRetryClick: () -> Unit,
) {
    var showDialog by remember { mutableStateOf(false) }

    when (state) {
        is SaveTierState.Form -> {

            TierForm(
                name = state.name,
                nameError = state.nameError,
                price = state.price,
                priceError = state.priceError,
                description = state.description,
                descriptionError = state.descriptionError,
                onNameChange = { onNameChange(it) },
                onPriceChange = { onPriceChange(it) },
                onDescriptionChange = { onDescriptionChange(it) },
                onSaveClick = { showDialog = true },
                modifier = Modifier.fillMaxSize()
                    .padding(paddingValues)
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
                            onSaveClick()
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
            ErrorScreen(onRetryClick = onRetryClick)
        }

        is SaveTierState.Loading -> {
            LoadingScreen(isLoading = true)
        }
    }
}
