package com.example.subscribe.presentation.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.subscribe.R
import com.example.subscribe.presentation.SubscribeViewModel
import com.example.subscribe.presentation.model.SubscribeAction
import com.example.subscribe.presentation.model.SubscribeEvent
import com.example.ui.view.composables.CenterAlignedTopAppBarWithBackButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubscribeScreen(viewModel: SubscribeViewModel) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val action by viewModel.actionsFlow.collectAsStateWithLifecycle(SubscribeAction.Initial)

    ObserveActions(action)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBarWithBackButton(
                title = stringResource(R.string.subscribe),
                onBackClick = { viewModel.obtainEvent(SubscribeEvent.BackClick) },
                actions = {
                    if (state.isCurrentUser && state.selectedTierId != -1L) {
                        IconButton(
                            onClick = { viewModel.obtainEvent(SubscribeEvent.EditTier) }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = stringResource(R.string.edit_tier),
                            )
                        }
                    }
                }
            )
        },
    ) { paddingValues ->
        PullToRefreshBox(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            isRefreshing = state.isRefreshing,
            onRefresh = { viewModel.obtainEvent(SubscribeEvent.Refresh) },
        ) {
            ObserveState(
                state = state,
                onSubscribeClick = {
                    viewModel.obtainEvent(
                        if (state.isCurrentUser) SubscribeEvent.AddNewTier
                        else SubscribeEvent.Subscribe
                    )
                },
                onTierSelected = { viewModel.obtainEvent(SubscribeEvent.SelectedTierChange(it)) },
                onRetryClick = { viewModel.obtainEvent(SubscribeEvent.Refresh) }
            )
        }
    }
}
