package com.example.savepost.presentation.composables

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.savepost.R
import com.example.savepost.presentation.SavePostViewModel
import com.example.savepost.presentation.model.SavePostAction
import com.example.savepost.presentation.model.SavePostEvent
import com.example.ui.view.composables.CenterAlignedTopAppBarWithBackButton

@Composable
fun SavePostScreen(viewModel: SavePostViewModel) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val action by viewModel.actionsFlow.collectAsStateWithLifecycle(
        initialValue = SavePostAction.Initiate
    )

    ObserveActions(action)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBarWithBackButton(
                title = stringResource(R.string.save_post),
                onBackClick = { viewModel.obtainEvent(SavePostEvent.BackClick) }
            )
        }
    ) { paddingValues ->
        ObserveState(
            uiState = uiState,
            paddingValues = paddingValues,
            onTitleChange = { viewModel.obtainEvent(SavePostEvent.TitleChange(it)) },
            onDescriptionChange = { viewModel.obtainEvent(SavePostEvent.DescriptionChange(it)) },
            onSaveClick = { viewModel.obtainEvent(SavePostEvent.SavePost) },
            onRetryClick = { viewModel.obtainEvent(SavePostEvent.RetryClick) }
        )
    }
}
