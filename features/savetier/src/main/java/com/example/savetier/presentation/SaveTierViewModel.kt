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
    initialState = SaveTierState.Loading
) {

    var tierId: Long = -1L

    init {
        loadTier()
    }

    override fun obtainEvent(event: SaveTierEvent) {
        when (event) {
            is SaveTierEvent.BackClick -> popBackStack()
            is SaveTierEvent.NameChange -> _uiState.value =
                (_uiState.value as SaveTierState.Form).copy(name = event.name)
            is SaveTierEvent.PriceChange -> _uiState.value =
                (_uiState.value as SaveTierState.Form).copy(price = event.price)
            is SaveTierEvent.DescriptionChange -> _uiState.value =
                (_uiState.value as SaveTierState.Form).copy(description = event.description)
            is SaveTierEvent.SaveTier -> saveTier()
            SaveTierEvent.RetryClick -> loadTier()
        }
    }

    private fun loadTier() {
        _uiState.value = SaveTierState.Loading
        viewModelScope.launch {
            runSuspendCatching(exceptionHandlerDelegate) {
                getTierUseCase.invoke(tierId)
            }.onSuccess {
                _uiState.value = SaveTierState.Form(
                    name = it?.name.orEmpty(),
                    price = it?.price ?: 0.0,
                    description = it?.description.orEmpty()
                )
            }.onFailure {
                _uiState.value = SaveTierState.Error
            }
        }
    }

    private fun saveTier() {
        val form = (_uiState.value as SaveTierState.Form)

        if (!validateForm(form)) return

        viewModelScope.launch {
            runSuspendCatching(exceptionHandlerDelegate) {
                saveTierUseCase.invoke(
                    TierDomainModel(
                        id = tierId,
                        name = form.name,
                        price = form.price,
                        description = form.description
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

    private fun validateForm(form: SaveTierState.Form): Boolean {
        val nameResult = validateTierFormUseCase.validateName(form.name)
        val priceResult = validateTierFormUseCase.validatePrice(form.price)
        val descriptionResult = validateTierFormUseCase.validateDescription(form.description)

        val hasError = listOf(
            nameResult,
            priceResult,
            descriptionResult
        ).any { it.isValid.not() }

        if (hasError) {
            _uiState.value = (_uiState.value as SaveTierState.Form).copy(
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