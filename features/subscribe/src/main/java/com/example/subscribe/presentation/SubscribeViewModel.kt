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
    initialState = SubscribeState()
) {

    override fun obtainEvent(event: SubscribeEvent) {
        when (event) {
            is SubscribeEvent.Initiate -> {
                _uiState.value = _uiState.value.copy(username = event.username)
                loadData()
            }
            is SubscribeEvent.SelectedTierChange ->
                _uiState.value = _uiState.value.copy(selectedTierId = event.tierId)
            is SubscribeEvent.Subscribe -> subscribe()
            is SubscribeEvent.EditTier -> navigateToSaveTierScreen(_uiState.value.selectedTierId)
            is SubscribeEvent.AddNewTier -> navigateToSaveTierScreen()
            is SubscribeEvent.BackClick -> popBackStack()
            is SubscribeEvent.Refresh -> loadData()
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
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    userDetails = getUserDetailsUseCase.invoke(_uiState.value.username).toUiModel(),
                    tiers = getTiersUseCase.invoke(_uiState.value.username),
                    isCurrentUser = isCurrentUserUseCase.invoke(_uiState.value.username),
                )
            }.onFailure {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    isError = true
                )
            }
        }
    }

    private fun navigateToSaveTierScreen(tierId: Long = -1L) {
        subscribeRouter.navigateToSaveTier(tierId)
    }

    private fun subscribe() {
        _uiState.value = _uiState.value.copy(isLoading = true)
        viewModelScope.launch {
            runSuspendCatching(exceptionHandlerDelegate) {
                subscribeUseCase.invoke(
                    tierId = _uiState.value.selectedTierId,
                    subscribedTo = _uiState.value.username
                )
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
