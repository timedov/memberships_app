package com.example.profile.presentation.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.profile.R
import com.example.profile.presentation.ProfileViewModel
import com.example.profile.presentation.model.ProfileEvent
import com.example.ui.view.composables.CenterAlignedTopAppBarWithBackButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(viewModel: ProfileViewModel) {
    val state by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBarWithBackButton(
                title = stringResource(id = R.string.profile),
                onBackClick = { viewModel.obtainEvent(ProfileEvent.BackClick) }
            )
        }
    ) { paddingValues ->
        PullToRefreshBox(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            isRefreshing = false,
            onRefresh = { viewModel.obtainEvent(ProfileEvent.Refresh) }
        ) {
            ObserveState(state, viewModel)
        }
    }
}
