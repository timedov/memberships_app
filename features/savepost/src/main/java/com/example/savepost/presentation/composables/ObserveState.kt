package com.example.savepost.presentation.composables

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.savepost.presentation.model.SavePostState
import com.example.ui.view.composables.LoadingScreen

@Composable
fun ObserveState(
    uiState: SavePostState,
    paddingValues: PaddingValues,
    onTitleChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onImageIconClick: () -> Unit,
    onVideoIconClick: () -> Unit
) {

    PostForm(
        title = uiState.title,
        titleError = uiState.titleError,
        onTitleChange = onTitleChange,
        description = uiState.description,
        descriptionError = uiState.descriptionError,
        onDescriptionChange = onDescriptionChange,
        onImageIconClick = onImageIconClick,
        onVideoIconClick = onVideoIconClick,
        modifier = Modifier.fillMaxWidth().padding(paddingValues)
    )

    LoadingScreen(isLoading = uiState.isLoading)
}
