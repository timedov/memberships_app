package com.example.profile.impl.presentation.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.*
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.profile.impl.R
import com.example.profile.impl.presentation.ProfileViewModel
import com.example.profile.impl.presentation.model.ProfileEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ProfileScreen(viewModel: ProfileViewModel) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val posts = state.postsFlow.collectAsLazyPagingItems()
    var showSignOutDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.profile),
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { viewModel.obtainEvent(ProfileEvent.BackClick) }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back)
                        )
                    }
                },
                actions = {
                    if (state.isCurrentUser) {
                        IconButton(onClick = { showSignOutDialog = true }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.Logout,
                                contentDescription = stringResource(id = R.string.sign_out)
                            )
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        PullToRefreshBox(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            isRefreshing = state.isRefreshing,
            onRefresh = {
                posts.refresh()
                viewModel.obtainEvent(ProfileEvent.Refresh)
            }
        ) {
            ObserveState(
                state = state,
                posts = posts,
                onPostClick = { viewModel.obtainEvent(ProfileEvent.PostClick(it)) },
                onSubscribeClick = { viewModel.obtainEvent(ProfileEvent.SubscribeClick) },
                onRetryClick = { viewModel.obtainEvent(ProfileEvent.Initiate(state.username)) },
            )
        }

        if (showSignOutDialog) {
            AlertDialog(
                onDismissRequest = { showSignOutDialog = false },
                title = { Text(text = stringResource(R.string.confirm_sign_out)) },
                text = { Text(text = stringResource(R.string.confirm_sign_out_desc)) },
                confirmButton = {
                    TextButton(onClick = {
                        showSignOutDialog = false
                        viewModel.obtainEvent(ProfileEvent.SignOut)
                    }) {
                        Text(text = stringResource(R.string.sign_out))
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showSignOutDialog = false }) {
                        Text(text = stringResource(R.string.cancel))
                    }
                }
            )
        }
    }
}
