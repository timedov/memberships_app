package com.example.savepost.impl.presentation

import androidx.lifecycle.viewModelScope
import com.example.common.utils.AppExceptionHandler
import com.example.common.utils.runSuspendCatching
import com.example.savepost.impl.presentation.model.SavePostAction
import com.example.savepost.impl.presentation.model.SavePostEvent
import com.example.savepost.impl.presentation.model.SavePostState
import com.example.savepost.impl.domain.usecase.SavePostInteractor
import com.example.ui.base.BaseViewModel
import com.example.ui.model.PostUiModel
import com.example.feed.api.presentation.utils.toDomainModel
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class SavePostViewModel @Inject constructor(
    private val interactor: SavePostInteractor,
    private val appExceptionHandler: AppExceptionHandler
) : BaseViewModel<SavePostState, SavePostEvent, SavePostAction>(initialState = SavePostState()) {

    init {
        hasPostDraft()
    }

    override fun obtainEvent(event: SavePostEvent) {
        when (event) {
            is SavePostEvent.BackClick -> onBackClicked()
            is SavePostEvent.TitleValueChange -> _uiState.value =
                _uiState.value.copy(title = event.title.trimStart(), titleError = "")
            is SavePostEvent.ContentValueChange -> onContentValueChanged(event.content)
            is SavePostEvent.DescriptionValueChange -> _uiState.value = _uiState.value.copy(
                description = event.description.trimStart(),
                descriptionError = ""
            )
            is SavePostEvent.RequireSubscriptionChange -> _uiState.value =
                _uiState.value.copy(requiresSubscription = event.requiresSubscription)
            is SavePostEvent.SavePost -> savePost()
            is SavePostEvent.RestoreDraft -> getPostDraft()
            is SavePostEvent.RemoveDraft -> removePostDraft()
        }
    }

    private fun hasPostDraft() =
        viewModelScope.launch {
            runSuspendCatching(appExceptionHandler) {
                interactor.hasPostDraft()
            }.onSuccess {
                if (it) _actionsFlow.emit(SavePostAction.ShowRestoreDraftDialog)
            }
        }

    private fun getPostDraft() {
        _uiState.value = _uiState.value.copy(isLoading = true, isError = false)
        viewModelScope.launch {
            runSuspendCatching(appExceptionHandler) { interactor.getPostDraft() }
                .onSuccess { draft ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        title = draft.title,
                        description = draft.body,
                        content = draft.content,
                        requiresSubscription = draft.requiresSubscription
                    )
                }
                .onFailure { _uiState.value = _uiState.value.copy(isLoading = false) }
        }
    }

    private fun onBackClicked() {
        viewModelScope.launch {
            savePostDraft()
            interactor.navigateToProfile()
        }
    }

    private fun validateForm(title: String, description: String): Boolean {
        val titleResult = interactor.validateTitle(title)
        val descriptionResult = interactor.validateDescription(description)
        val hasError = listOf(titleResult, descriptionResult).any { !it.isValid }
        if (hasError) {
            _uiState.value = _uiState.value.copy(
                titleError = titleResult.errorMessage.orEmpty(),
                descriptionError = descriptionResult.errorMessage.orEmpty()
            )
        }
        return !hasError
    }

    private fun savePost() {
        if (validateForm(_uiState.value.title, _uiState.value.description)) {
            viewModelScope.launch {
                runSuspendCatching(appExceptionHandler) { savePostDraft() }
                    .onSuccess { _actionsFlow.emit(SavePostAction.SaveSuccess) }
                    .onFailure { _actionsFlow.emit(SavePostAction.SaveError) }
                interactor.uploadPost()
                interactor.navigateToProfile()
            }
        }
    }

    private suspend fun savePostDraft() {
        if (
            _uiState.value.title.isNotEmpty()
            || _uiState.value.description.isNotEmpty()
            || _uiState.value.content.isNotEmpty()
        ) {
            interactor.savePostDraft(
                PostUiModel(
                    title = _uiState.value.title.trim(),
                    body = _uiState.value.description.trim(),
                    content = _uiState.value.content,
                    requiresSubscription = _uiState.value.requiresSubscription
                ).toDomainModel()
            )
        }
    }

    private fun onContentValueChanged(content: String) {
        _uiState.value = _uiState.value.copy(content = content)
    }

    private fun removePostDraft() {
        viewModelScope.launch {
            runSuspendCatching(appExceptionHandler) { interactor.removePostDraft() }
        }
    }
}
