package com.example.profile.presentation.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.profile.presentation.ProfileViewModel
import com.example.profile.presentation.model.ProfileEvent
import com.example.profile.presentation.model.ProfileState
import com.example.ui.view.composables.ErrorScreen
import com.example.ui.view.composables.LoadingScreen

@Composable
fun ObserveState(
    state: ProfileState,
    viewModel: ProfileViewModel
) {

    when (state) {
        is ProfileState.Loading -> LoadingScreen(isLoading = true)
        is ProfileState.Content -> {
            ProfileContent(
                userDetails = state.userDetails,
                subscribers = state.subscribers,
                response = state.posts,
                onSubscribeClick = { viewModel.obtainEvent(ProfileEvent.SubscribeClick) },
                onPostClick = { viewModel.obtainEvent(ProfileEvent.PostClick(it)) },
                modifier = Modifier.fillMaxSize()
            )
        }
        is ProfileState.Error -> ErrorScreen { viewModel.obtainEvent(ProfileEvent.Refresh) }
    }
}