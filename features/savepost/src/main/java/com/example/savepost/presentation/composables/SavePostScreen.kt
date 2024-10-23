package com.example.savepost.presentation.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
                onBackClick = { viewModel.obtainEvent(SavePostEvent.BackClick) },
                actions = {
                    IconButton(
                        onClick = { viewModel.obtainEvent(SavePostEvent.SavePost) },
                        enabled = uiState.titleError.isEmpty() && uiState.descriptionError.isEmpty()
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Send,
                            contentDescription = stringResource(R.string.save_post),
                        )
                    }
                }
            )
        },
    ) { paddingValues ->
        ObserveState(
            uiState = uiState,
            paddingValues = paddingValues,
            onTitleChange = { viewModel.obtainEvent(SavePostEvent.TitleValueChange(it)) },
            onDescriptionChange =
                { viewModel.obtainEvent(SavePostEvent.DescriptionValueChange(it)) },
            onImageIconClick = { viewModel.obtainEvent(SavePostEvent.Initiate) },
            onVideoIconClick = { viewModel.obtainEvent(SavePostEvent.Initiate) }
        )
    }
}
