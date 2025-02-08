package com.example.profile.impl.presentation

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.common.utils.AppExceptionHandler
import com.example.common.utils.runSuspendCatching
import com.example.feed.api.presentation.utils.toUiModel
import com.example.profile.api.presentation.utils.toUiModel
import com.example.profile.impl.domain.usecase.ProfileInteractor
import com.example.profile.impl.presentation.model.ProfileAction
import com.example.profile.impl.presentation.model.ProfileEvent
import com.example.profile.impl.presentation.model.ProfileState
import com.example.ui.base.BaseViewModel
import com.example.ui.utils.subscribersCountToPrettyFormat
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class ProfileViewModel @Inject constructor(
    private val profileInteractor: ProfileInteractor,
    private val appExceptionHandler: AppExceptionHandler
) : BaseViewModel<ProfileState, ProfileEvent, ProfileAction>(
    initialState = ProfileState()
) {

    override fun obtainEvent(event: ProfileEvent) {
        when (event) {
            is ProfileEvent.Initiate -> loadData(event.username)
            is ProfileEvent.Refresh -> loadData(_uiState.value.username)
            is ProfileEvent.SubscribeClick -> subscribe()
            is ProfileEvent.PostClick -> profileInteractor.navigateToPostDetails(event.postId)
            is ProfileEvent.BackClick -> profileInteractor.popBackStack()
            is ProfileEvent.SignOut -> signOut()
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
            runSuspendCatching(appExceptionHandler) {
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
            runSuspendCatching(appExceptionHandler) {
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
            runSuspendCatching(appExceptionHandler) {
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
            runSuspendCatching(appExceptionHandler) {
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
            runSuspendCatching(appExceptionHandler) {
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
                .cachedIn(viewModelScope)
                .map { pagingData ->
                    pagingData.map { it.toUiModel() }
                }
        )
    }

    private fun subscribe() {
        viewModelScope.launch {
            runSuspendCatching(appExceptionHandler) {
                profileInteractor.subscribe(_uiState.value.username)
            }.onSuccess {
                _uiState.value = _uiState.value.copy(isSubscribed = it)
                loadSubscribers()
            }.onFailure {
                _uiState.value = _uiState.value.copy(isError = true, isLoading = false)
            }
        }
    }

    private fun signOut() {
        viewModelScope.launch {
            runSuspendCatching(appExceptionHandler) {
                profileInteractor.signOut()
            }.onSuccess {
                profileInteractor.navigateToSignIn()
            }.onFailure {
                _uiState.value = _uiState.value.copy(isError = true, isLoading = false)
            }
        }
    }
}
