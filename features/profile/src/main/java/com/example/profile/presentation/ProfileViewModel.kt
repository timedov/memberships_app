package com.example.profile.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.common.utils.ExceptionHandlerDelegate
import com.example.common.utils.runSuspendCatching
import com.example.domain.repository.PostRepository
import com.example.profile.usecase.GetSubscribersUseCase
import com.example.profile.usecase.GetUserDetailsUseCase
import com.example.profile.navigation.ProfileRouter
import com.example.profile.presentation.model.ProfileAction
import com.example.profile.presentation.model.ProfileEvent
import com.example.profile.presentation.model.ProfileState
import com.example.ui.base.BaseViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

class ProfileViewModel @AssistedInject constructor(
    @Assisted private val username: String,
    private val profileRouter: ProfileRouter,
    private val getUserDetailsUseCase: GetUserDetailsUseCase,
    private val getSubscribersUseCase: GetSubscribersUseCase,
    private val postRepository: PostRepository,
    private val exceptionHandlerDelegate: ExceptionHandlerDelegate
) : BaseViewModel<ProfileState, ProfileEvent, ProfileAction>(
    initialState = ProfileState.Loading
) {

    init {
        loadUserDetails()
    }

    override fun obtainEvent(event: ProfileEvent) {
        when (event) {
            ProfileEvent.Refresh -> loadUserDetails()
            ProfileEvent.SubscribeClick -> profileRouter.navigateToSubscribe()
            is ProfileEvent.PostClick -> profileRouter.navigateToPostDetails(event.postId)
            ProfileEvent.BackClick -> profileRouter.popBackStack()
        }
    }

    private fun loadUserDetails() {
        viewModelScope.launch {
            runSuspendCatching(exceptionHandlerDelegate) {
                getUserDetailsUseCase.invoke(username)
            }.onSuccess {
                _uiState.value = ProfileState.Content(
                    userDetails = it,
                    posts = postRepository.getPostsOfUser(username)
                )
                loadSubscribers()
            }.onFailure {
                _uiState.value = ProfileState.Error(it.message.orEmpty())
            }
        }
    }

    private fun loadSubscribers() {
        viewModelScope.launch {
            runSuspendCatching(exceptionHandlerDelegate) {
                getSubscribersUseCase.invoke(username)
            }.onSuccess {
                _uiState.value = (_uiState.value as ProfileState.Content).copy(
                    subscribers = it
                )
            }.onFailure {
                _uiState.value = ProfileState.Error(it.message.orEmpty())
            }
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(username: String): ProfileViewModel
    }

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun provideFactory(
            assistedFactory: Factory,
            assistedParam: String,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {

            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(assistedParam) as T
            }
        }
    }
}