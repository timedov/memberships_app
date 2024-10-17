package com.example.profile.presentation

import androidx.lifecycle.viewModelScope
import com.example.common.utils.ExceptionHandlerDelegate
import com.example.common.utils.runSuspendCatching
import com.example.domain.usecase.IsCurrentUserUseCase
import com.example.profile.navigation.ProfileRouter
import com.example.profile.presentation.model.ProfileAction
import com.example.profile.presentation.model.ProfileEvent
import com.example.profile.presentation.model.ProfileState
import com.example.profile.usecase.GetPostsOfUserUseCase
import com.example.profile.usecase.GetSubscribersUseCase
import com.example.profile.usecase.GetUserDetailsUseCase
import com.example.ui.base.BaseViewModel
import com.example.ui.utils.toUiModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val profileRouter: ProfileRouter,
    private val isCurrentUserUseCase: IsCurrentUserUseCase,
    private val getUserDetailsUseCase: GetUserDetailsUseCase,
    private val getSubscribersUseCase: GetSubscribersUseCase,
    private val getPostsOfUserUseCase: GetPostsOfUserUseCase,
    private val exceptionHandlerDelegate: ExceptionHandlerDelegate
) : BaseViewModel<ProfileState, ProfileEvent, ProfileAction>(
    initialState = ProfileState()
) {

    override fun obtainEvent(event: ProfileEvent) {
        when (event) {
            is ProfileEvent.Initiate -> {
                _uiState.value = _uiState.value.copy(
                    username = event.username
                )
                loadData()
            }
            is ProfileEvent.Refresh -> loadData()
            is ProfileEvent.SubscribeClick ->
                profileRouter.navigateToSubscribe(username = _uiState.value.username)
            is ProfileEvent.PostClick -> profileRouter.navigateToPostDetails(event.postId)
            is ProfileEvent.BackClick -> profileRouter.popBackStack()
        }
    }

    private fun loadData() {
        _uiState.value = _uiState.value.copy(
            isLoading = true,
            isRefreshing = false,
            isError = false
        )
        viewModelScope.launch {
            runSuspendCatching(exceptionHandlerDelegate) {
                getUserDetailsUseCase.invoke(_uiState.value.username)
            }.onSuccess {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    isCurrentUser = isCurrentUserUseCase.invoke(_uiState.value.username),
                    userDetails = it.toUiModel(),
                    postsFlow = getPostsOfUserUseCase.invoke(_uiState.value.username),
                    subscribers = getSubscribersUseCase.invoke(_uiState.value.username),
                )
            }.onFailure {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    isRefreshing = false,
                    isError = true
                )
            }
        }
    }
}