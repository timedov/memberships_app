package com.example.savetier.presentation

import androidx.lifecycle.viewModelScope
import com.example.common.utils.ExceptionHandlerDelegate
import com.example.common.utils.runSuspendCatching
import com.example.domain.model.TierDomainModel
import com.example.savetier.navigation.SaveTierRouter
import com.example.savetier.presentation.model.SaveTierAction
import com.example.savetier.presentation.model.SaveTierEvent
import com.example.savetier.presentation.model.SaveTierState
import com.example.savetier.usecase.GetTierUseCase
import com.example.savetier.usecase.SaveTierUseCase
import com.example.savetier.usecase.ValidateTierFormUseCase
import com.example.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class SaveTierViewModel @Inject constructor(
    private val saveTierRouter: SaveTierRouter,
    private val getTierUseCase: GetTierUseCase,
    private val saveTierUseCase: SaveTierUseCase,
    private val validateTierFormUseCase: ValidateTierFormUseCase,
    private val exceptionHandlerDelegate: ExceptionHandlerDelegate
) : BaseViewModel<SaveTierState, SaveTierEvent, SaveTierAction>(
    initialState = SaveTierState()
) {

    override fun obtainEvent(event: SaveTierEvent) {
        when (event) {
            is SaveTierEvent.Initiate -> {
                _uiState.value = _uiState.value.copy(tierId = event.tierId)
                loadTier()
            }
            is SaveTierEvent.BackClick -> popBackStack()
            is SaveTierEvent.NameChange ->
                _uiState.value = _uiState.value.copy(name = event.name)
            is SaveTierEvent.PriceChange ->
                _uiState.value = _uiState.value.copy(price = event.price)
            is SaveTierEvent.DescriptionChange ->
                _uiState.value = _uiState.value.copy(description = event.description)
            is SaveTierEvent.SaveTier -> saveTier()
            SaveTierEvent.RetryClick -> loadTier()
        }
    }

    private fun loadTier() {
        _uiState.value = _uiState.value.copy(
            isLoading = true,
            isError = false
        )
        viewModelScope.launch {
            runSuspendCatching(exceptionHandlerDelegate) {
                getTierUseCase.invoke(_uiState.value.tierId)
            }.onSuccess {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    name = it.name,
                    price = it.price,
                    description = it.description
                )
            }.onFailure {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    isError = true
                )
            }
        }
    }

    private fun saveTier() {
        if (!validateForm(
                name = _uiState.value.name,
                price = _uiState.value.price,
                description = _uiState.value.description
            )) return

        viewModelScope.launch {
            runSuspendCatching(exceptionHandlerDelegate) {
                saveTierUseCase.invoke(
                    TierDomainModel(
                        id = _uiState.value.tierId,
                        name = _uiState.value.name,
                        price = _uiState.value.price,
                        description = _uiState.value.description
                    )
                )
            }.onSuccess {
                _actionsFlow.emit(SaveTierAction.SaveSuccess)
            }.onFailure {
                _actionsFlow.emit(SaveTierAction.SaveError)
            }
            popBackStack()
        }
    }

    private fun validateForm(
        name: String,
        price: Double,
        description: String
    ): Boolean {
        val nameResult = validateTierFormUseCase.validateName(name)
        val priceResult = validateTierFormUseCase.validatePrice(price)
        val descriptionResult = validateTierFormUseCase.validateDescription(description)

        val hasError = listOf(
            nameResult,
            priceResult,
            descriptionResult
        ).any { it.isValid.not() }

        if (hasError) {
            _uiState.value = _uiState.value.copy(
                nameError = nameResult.errorMessage,
                priceError = priceResult.errorMessage,
                descriptionError = descriptionResult.errorMessage
            )
        }

        return hasError.not()
    }

    private fun popBackStack() {
        saveTierRouter.popBackStack()
    }
}