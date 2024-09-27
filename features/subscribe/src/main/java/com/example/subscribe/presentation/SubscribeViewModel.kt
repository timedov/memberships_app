package com.example.subscribe.presentation

import androidx.lifecycle.viewModelScope
import com.example.common.utils.ExceptionHandlerDelegate
import com.example.common.utils.runSuspendCatching
import com.example.domain.usecase.GetUserDetailsUseCase
import com.example.domain.usecase.IsCurrentUserUseCase
import com.example.subscribe.navigation.SubscribeRouter
import com.example.subscribe.presentation.model.SubscribeAction
import com.example.subscribe.presentation.model.SubscribeEvent
import com.example.subscribe.presentation.model.SubscribeState
import com.example.subscribe.usecase.GetTiersUseCase
import com.example.subscribe.usecase.SubscribeUseCase
import com.example.ui.base.BaseViewModel
import com.example.ui.utils.toUiModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class SubscribeViewModel @Inject constructor(
    private val subscribeRouter: SubscribeRouter,
    private val subscribeUseCase: SubscribeUseCase,
    private val getTiersUseCase: GetTiersUseCase,
    private val getUserDetailsUseCase: GetUserDetailsUseCase,
    private val isCurrentUserUseCase: IsCurrentUserUseCase,
    private val exceptionHandlerDelegate: ExceptionHandlerDelegate
) : BaseViewModel<SubscribeState, SubscribeEvent, SubscribeAction>(
    initialState = SubscribeState.Loading
) {

    private var username: String = ""

    init {
        loadUserDetails()
    }

    private fun loadUserDetails() {
        viewModelScope.launch {
            _uiState.value = SubscribeState.Loading
            runSuspendCatching(exceptionHandlerDelegate) {
                getUserDetailsUseCase.invoke(username)
            }.onSuccess {
                _uiState.value = SubscribeState.Content(
                    userDetails = it.toUiModel(),
                    tiers = emptyList(),
                    isCurrentUser = true
                )
                loadTiers()
            }.onFailure {
                _uiState.value = SubscribeState.Error
            }
        }
    }

    private fun loadTiers() {
        viewModelScope.launch {
            _uiState.value = SubscribeState.Loading
            runSuspendCatching(exceptionHandlerDelegate) {
                getTiersUseCase.invoke(username)
            }.onSuccess {
                _uiState.value = (_uiState.value as SubscribeState.Content).copy(tiers = it)
                checkIsCurrentUser()
            }.onFailure {
                _uiState.value = SubscribeState.Error
            }
        }
    }

    private fun checkIsCurrentUser() {
        viewModelScope.launch {
            runSuspendCatching(exceptionHandlerDelegate) {
                isCurrentUserUseCase.invoke(username)
            }.onSuccess {
                _uiState.value =
                    (_uiState.value as SubscribeState.Content).copy(isCurrentUser = it)
            }.onFailure {
                _uiState.value = SubscribeState.Error
            }
        }
    }

    override fun obtainEvent(event: SubscribeEvent) {
        when (event) {
            is SubscribeEvent.Initiate -> username = event.username
            is SubscribeEvent.Subscribe -> subscribe(event.tierId)
            is SubscribeEvent.EditTier -> navigateToSaveTierScreen(event.tierId)
            is SubscribeEvent.AddNewTier -> navigateToSaveTierScreen()
            is SubscribeEvent.BackClick -> popBackStack()
            is SubscribeEvent.Refresh -> loadTiers()
        }
    }

    private fun navigateToSaveTierScreen(tierId: Long = -1L) {
        subscribeRouter.navigateToSaveTier(tierId)
    }

    private fun subscribe(tierId: Long) {
        _uiState.value = SubscribeState.Loading
        viewModelScope.launch {
            runSuspendCatching(exceptionHandlerDelegate) {
                subscribeUseCase.invoke(tierId = tierId, subscribedTo = username)
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
}
