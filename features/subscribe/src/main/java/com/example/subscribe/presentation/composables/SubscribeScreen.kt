package com.example.subscribe.presentation.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.domain.model.TierModel
import com.example.domain.model.UserDetailsModel
import com.example.subscribe.R
import com.example.subscribe.presentation.SubscribeViewModel
import com.example.subscribe.presentation.model.SubscribeEvent
import com.example.subscribe.presentation.model.SubscribeState
import com.example.ui.view.composables.CenterAlignedTopAppBarWithBackButton
import com.example.ui.view.composables.ErrorScreen
import com.example.ui.view.composables.LoadingScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubscribeScreen(viewModel: SubscribeViewModel) {
    val state by viewModel.uiState.collectAsState()
    val action by viewModel.actionsFlow.collectAsState(null)

    var isRefreshing by remember { mutableStateOf(false) }
    var isCurrentUser by remember { mutableStateOf(false) }
    var selectedTier by remember { mutableStateOf<TierModel?>(null) }
    val snackbarHostState = remember { SnackbarHostState() }

    ObserveActions(action)

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            CenterAlignedTopAppBarWithBackButton(
                title = stringResource(R.string.subscribe),
                onBackClick = { viewModel.obtainEvent(SubscribeEvent.BackClick) },
                actions = {
                    if (isCurrentUser) {
                        selectedTier?.let {
                            IconButton(
                                onClick = { viewModel.obtainEvent(SubscribeEvent.EditTier(it.id)) }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Edit,
                                    contentDescription = stringResource(R.string.edit_tier),
                                )
                            }
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
            isRefreshing = isRefreshing,
            onRefresh = {
                viewModel.obtainEvent(SubscribeEvent.Refresh)
                isRefreshing = true
            },
        ) {
            when (val currentState = state) {
                is SubscribeState.Content -> {
                    isRefreshing = false
                    isCurrentUser = currentState.isCurrentUser

                    SubscribeContent(
                        userDetails = UserDetailsModel(),
                        tiers = currentState.tiers,
                        buttonTitle = stringResource(
                            id = if (isCurrentUser) R.string.plus else R.string.subscribe
                        ),
                        selectedTier = selectedTier,
                        onTierSelected = { selectedTier = it },
                        onButtonClick = {
                            if (isCurrentUser) {
                                viewModel.obtainEvent(SubscribeEvent.AddNewTier)
                            } else {
                                selectedTier?.let {
                                    viewModel.obtainEvent(SubscribeEvent.Subscribe(it.id))
                                }
                            }
                        }
                    )
                }
                SubscribeState.Error -> {
                    isRefreshing = false
                    ErrorScreen(
                        onRetryClick = { viewModel.obtainEvent(SubscribeEvent.Refresh) }
                    )
                }
                SubscribeState.Loading -> {
                    LoadingScreen(isLoading = true)
                }
            }
        }
    }
}
