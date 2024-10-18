package com.example.savepost.presentation.composables

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import com.example.savepost.presentation.model.SavePostState
import com.example.ui.view.composables.ErrorScreen
import com.example.ui.view.composables.LoadingScreen

@Composable
fun ObserveState(
    uiState: SavePostState,
    paddingValues: PaddingValues,
    onTitleChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onSaveClick: () -> Unit,
    onRetryClick: () -> Unit,
) {

    if (uiState.isError) {
        ErrorScreen(onRetryClick = onRetryClick)
    } else {

        LoadingScreen(isLoading = uiState.isLoading)
    }
}
