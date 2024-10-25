package com.example.savepost.presentation

import androidx.core.net.toUri
import androidx.lifecycle.viewModelScope
import com.example.common.utils.ExceptionHandlerDelegate
import com.example.common.utils.runSuspendCatching
import com.example.savepost.presentation.model.SavePostAction
import com.example.savepost.presentation.model.SavePostEvent
import com.example.savepost.presentation.model.SavePostState
import com.example.savepost.usecase.SavePostInteractor
import com.example.ui.base.BaseViewModel
import com.example.ui.model.PostDataUiModel
import com.example.ui.utils.toDomainModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class SavePostViewModel @Inject constructor(
    private val interactor: SavePostInteractor,
    private val exceptionHandlerDelegate: ExceptionHandlerDelegate
) : BaseViewModel<SavePostState, SavePostEvent, SavePostAction>(
    initialState = SavePostState(player = interactor.getPlayer())
) {

    init { interactor.preparePlayer() }

    override fun obtainEvent(event: SavePostEvent) {
        when (event) {
            is SavePostEvent.Initiate -> if (event.loadDraft) getPostDraft()
            is SavePostEvent.BackClick -> interactor.popBackStack()
            is SavePostEvent.TitleValueChange -> _uiState.value =
                _uiState.value.copy(title = event.title, titleError = "")
            is SavePostEvent.ContentValueChange -> _uiState.value =
                _uiState.value.copy(content = event.uri, contentType = event.contentType)
            is SavePostEvent.DescriptionValueChange -> _uiState.value =
                    _uiState.value.copy(description = event.description, descriptionError = "")
            is SavePostEvent.RequireSubscriptionChange -> _uiState.value =
                _uiState.value.copy(requiresSubscription = event.requiresSubscription)
            is SavePostEvent.SavePost -> savePost()
            is SavePostEvent.Retry -> getPostDraft()
        }
    }

    private fun getPostDraft() {
        _uiState.value = _uiState.value.copy(isLoading = true, isError = false)
        viewModelScope.launch {
            runSuspendCatching(exceptionHandlerDelegate) {
                interactor.getPostDraft()
            }.onSuccess {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    title = it.title,
                    description = it.body,
                    content = it.content.toUri(),
                    contentType = it.contentType,
                    requiresSubscription = it.requiresSubscription
                )
            }.onFailure {
                _uiState.value = _uiState.value.copy(isLoading = false, isError = true)
            }
        }
    }

    private fun savePost() {
        if (validateForm(title = _uiState.value.title, description = _uiState.value.description)) {
            viewModelScope.launch {
                runSuspendCatching(exceptionHandlerDelegate) {
                    interactor.savePostDraft(
                        PostDataUiModel(
                            title = _uiState.value.title,
                            body = _uiState.value.description,
                            content = _uiState.value.content.toString(),
                            contentType = _uiState.value.contentType,
                            requiresSubscription = _uiState.value.requiresSubscription
                        ).toDomainModel()
                    )
                }.onSuccess {
                    _actionsFlow.emit(SavePostAction.SaveSuccess)
                }.onFailure {
                    _actionsFlow.emit(SavePostAction.SaveError)
                }
                interactor.popBackStack()
            }
        }
    }

    private fun validateForm(
        title: String,
        description: String
    ): Boolean {
        val titleResult = interactor.validateTitle(title)
        val descriptionResult = interactor.validateDescription(description)

        val hasError = listOf(titleResult, descriptionResult).any { it.isValid.not() }

        if (hasError) {
            _uiState.value = _uiState.value.copy(
                titleError = titleResult.errorMessage.orEmpty(),
                descriptionError = descriptionResult.errorMessage.orEmpty()
            )
        }

        return hasError.not()
    }

    override fun onCleared() {
        super.onCleared()

        interactor.releasePlayer()
    }
}
