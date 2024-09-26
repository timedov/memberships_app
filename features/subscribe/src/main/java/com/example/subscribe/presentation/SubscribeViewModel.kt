package com.example.subscribe.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.common.utils.ExceptionHandlerDelegate
import com.example.common.utils.runSuspendCatching
import com.example.domain.repository.SubscribeRepository
import com.example.domain.repository.TierRepository
import com.example.domain.repository.UserRepository
import com.example.subscribe.navigation.SubscribeRouter
import com.example.subscribe.presentation.model.SubscribeAction
import com.example.subscribe.presentation.model.SubscribeEvent
import com.example.subscribe.presentation.model.SubscribeState
import com.example.ui.base.BaseViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

class SubscribeViewModel @AssistedInject constructor(
    @Assisted private val username: String,
    private val subscribeRouter: SubscribeRouter,
    private val subscribeRepository: SubscribeRepository,
    private val tierRepository: TierRepository,
    private val userRepository: UserRepository,
    private val exceptionHandlerDelegate: ExceptionHandlerDelegate
) : BaseViewModel<SubscribeState, SubscribeEvent, SubscribeAction>(
    initialState = SubscribeState.Loading
) {

    init {
        loadTiers()
    }

    private fun loadTiers() {
        viewModelScope.launch {
            _uiState.value = SubscribeState.Loading
            runSuspendCatching(exceptionHandlerDelegate) {
                tierRepository.getTiersByUser(username)
            }.onSuccess {
                _uiState.value = SubscribeState.Content(tiers = it, false)
                checkIsCurrentUser()
            }.onFailure {
                _uiState.value = SubscribeState.Error
            }
        }
    }

    private fun checkIsCurrentUser() {
        viewModelScope.launch {
            runSuspendCatching(exceptionHandlerDelegate) {
                userRepository.getCurrentUserCredentials()
            }.onSuccess {
                _uiState.value =
                    (_uiState.value as SubscribeState.Content).copy(isCurrentUser = it == username)
            }.onFailure {
                _uiState.value = SubscribeState.Error
            }
        }
    }

    override fun obtainEvent(event: SubscribeEvent) {
        when (event) {
            is SubscribeEvent.Subscribe -> subscribe(event.tierId)
            is SubscribeEvent.EditTier -> navigateToSaveTierScreen(event.tierId)
            SubscribeEvent.AddNewTier -> navigateToSaveTierScreen()
            SubscribeEvent.BackClick -> popBackStack()
            SubscribeEvent.Refresh -> loadTiers()
        }
    }

    private fun navigateToSaveTierScreen(id: Long = -1L) {
        subscribeRouter.navigateToSaveTier(id)
    }

    private fun subscribe(tierId: Long) {
        viewModelScope.launch {
            runSuspendCatching(exceptionHandlerDelegate) {
                subscribeRepository.subscribeToTier(tierId)
            }.onSuccess {
                _actionsFlow.emit(SubscribeAction.SubscribeSuccess)
            }.onFailure {
                _actionsFlow.emit(SubscribeAction.SubscribeError)
            }
            popBackStack()
        }
    }

    private fun popBackStack() {
        subscribeRouter.popBackStack()
    }

    @AssistedFactory
    interface Factory {
        fun create(username: String): SubscribeViewModel
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
