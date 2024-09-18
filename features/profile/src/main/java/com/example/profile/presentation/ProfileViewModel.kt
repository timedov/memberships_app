package com.example.profile.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.common.utils.ExceptionHandlerDelegate
import com.example.domain.model.UserDetailsModel
import com.example.domain.repository.UserRepository
import com.example.profile.navigation.ProfileRouter
import com.example.profile.presentation.model.ProfileAction
import com.example.profile.presentation.model.ProfileEvent
import com.example.profile.presentation.model.ProfileState
import com.example.ui.base.BaseViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileViewModel @AssistedInject constructor(
    @Assisted private val username: String,
    private val profileRouter: ProfileRouter,
    private val userRepository: UserRepository,
    private val exceptionHandlerDelegate: ExceptionHandlerDelegate
) : BaseViewModel<ProfileState, ProfileEvent, ProfileAction>(
    initialState = ProfileState.Loading
) {

    init {
        loadUserDetails()
    }

    override fun obtainEvent(event: ProfileEvent) {
        when (event) {
            ProfileEvent.BackClick -> profileRouter.popBackStack()
            ProfileEvent.SubscribeClick -> profileRouter.navigateToSubscribe()
            is ProfileEvent.PostClick -> profileRouter.navigateToPostDetails(event.postId)
            ProfileEvent.Refresh -> loadUserDetails()
        }
    }

    private fun loadUserDetails() {
        viewModelScope.launch {
            _uiState.value = ProfileState.UserDetails(
                userDetails = UserDetailsModel(
                    username = username,
                    imageUrl = null,
                    subscribers = "1,7k",
                    joinedYear = 2022,
                    about = "Android Developer"
                )
            )
        }
    }


    private fun loadPosts() {
        viewModelScope.launch {
            _uiState.value = ProfileState.Posts(
                posts = emptyList()
            )
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