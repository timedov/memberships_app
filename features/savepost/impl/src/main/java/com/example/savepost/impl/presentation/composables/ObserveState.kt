package com.example.savepost.impl.presentation.composables

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.savepost.impl.presentation.model.SavePostState
import com.example.ui.view.composable.LoadingScreen

@Composable
internal fun ObserveState(
    uiState: SavePostState,
    paddingValues: PaddingValues,
    onTitleChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onRemoveClick: () -> Unit,
    onImageIconClick: () -> Unit,
    onRequireSubscriptionChange: (Boolean) -> Unit
) {

    PostForm(
        title = uiState.title,
        titleError = uiState.titleError,
        content = uiState.content,
        description = uiState.description,
        descriptionError = uiState.descriptionError,
        requiresSubscription = uiState.requiresSubscription,
        onTitleChange = onTitleChange,
        onDescriptionChange = onDescriptionChange,
        onRemoveClick = onRemoveClick,
        onImageIconClick = onImageIconClick,
        onRequireSubscriptionChange = onRequireSubscriptionChange,
        modifier = Modifier.fillMaxWidth().padding(paddingValues)
    )

    LoadingScreen(isLoading = uiState.isLoading)
}
