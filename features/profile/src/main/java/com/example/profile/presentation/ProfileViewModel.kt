package com.example.profile.presentation

import androidx.lifecycle.viewModelScope
import com.example.common.utils.ExceptionHandlerDelegate
import com.example.common.utils.runSuspendCatching
import com.example.profile.presentation.model.ProfileAction
import com.example.profile.presentation.model.ProfileEvent
import com.example.profile.presentation.model.ProfileState
import com.example.profile.usecase.ProfileInteractor
import com.example.ui.base.BaseViewModel
import com.example.ui.utils.subscribersCountToPrettyFormat
import com.example.ui.utils.toUiModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val profileInteractor: ProfileInteractor,
    private val exceptionHandlerDelegate: ExceptionHandlerDelegate
) : BaseViewModel<ProfileState, ProfileEvent, ProfileAction>(
    initialState = ProfileState()
) {

    override fun obtainEvent(event: ProfileEvent) {
        when (event) {
            is ProfileEvent.Initiate -> loadData(event.username)
            is ProfileEvent.Refresh -> loadData(_uiState.value.username)
            is ProfileEvent.SubscribeClick ->
                profileInteractor.navigateToSubscribeScreen(username = _uiState.value.username)
            is ProfileEvent.PostClick -> profileInteractor.navigateToPostDetailsScreen(event.postId)
            is ProfileEvent.BackClick -> profileInteractor.popBackStack()
        }
    }

    private fun loadData(username: String) {
        _uiState.value = _uiState.value.copy(
            isLoading = true,
            isRefreshing = false,
            username = username,
            isError = false
        )

        if (_uiState.value.username.isEmpty()) getCurrentUsername()
        else isCurrentUser()
    }

    private fun getCurrentUsername() {
        viewModelScope.launch {
            runSuspendCatching(exceptionHandlerDelegate) {
                profileInteractor.getCurrentUsername()
            }.onSuccess {
                _uiState.value = _uiState.value.copy(username = it, isCurrentUser = true)
                loadUserDetails()
            }.onFailure {
                _uiState.value = _uiState.value.copy(isError = true, isLoading = false)
            }
        }
    }

    private fun isCurrentUser() {
        viewModelScope.launch {
            runSuspendCatching(exceptionHandlerDelegate) {
                profileInteractor.isCurrentUser(_uiState.value.username)
            }.onSuccess {
                _uiState.value = _uiState.value.copy(isCurrentUser = it)
                if (it) loadUserDetails() else isUserSubscribed()
            }.onFailure {
                _uiState.value = _uiState.value.copy(isError = true, isLoading = false)
            }
        }
    }

    private fun isUserSubscribed() {
        viewModelScope.launch {
            runSuspendCatching(exceptionHandlerDelegate) {
                profileInteractor.isUserSubscribed(_uiState.value.username)
            }.onSuccess {
                _uiState.value = _uiState.value.copy(isSubscribed = it)
                loadUserDetails()
            }.onFailure {
                _uiState.value = _uiState.value.copy(isError = true, isLoading = false)
            }
        }
    }

    private fun loadUserDetails() {
        viewModelScope.launch {
            runSuspendCatching(exceptionHandlerDelegate) {
                profileInteractor.getUserDetails(_uiState.value.username)
            }.onSuccess {
                _uiState.value = _uiState.value.copy(userDetails = it.toUiModel())
                loadSubscribers()
                loadPosts()
            }.onFailure {
                _uiState.value = _uiState.value.copy(isError = true, isLoading = false)
            }
        }
    }

    private fun loadSubscribers() {
        viewModelScope.launch {
            runSuspendCatching(exceptionHandlerDelegate) {
                profileInteractor.getSubscribersCount(_uiState.value.username)
            }.onSuccess {
                _uiState.value = _uiState.value.copy(
                    subscribers = it.subscribersCountToPrettyFormat(),
                    isLoading = false
                )
            }.onFailure {
                _uiState.value = _uiState.value.copy(isError = true, isLoading = false)
            }
        }
    }

    private fun loadPosts() {
        _uiState.value = _uiState.value.copy(
            postsFlow = profileInteractor.getUserPosts(_uiState.value.username)
        )
    }
}
