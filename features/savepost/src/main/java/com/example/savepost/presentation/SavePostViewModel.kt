package com.example.savepost.presentation

import android.net.Uri
import androidx.lifecycle.viewModelScope
import androidx.media3.common.Player
import com.example.common.utils.ExceptionHandlerDelegate
import com.example.common.utils.runSuspendCatching
import com.example.domain.model.ContentType
import com.example.savepost.navigation.SavePostRouter
import com.example.savepost.presentation.model.SavePostAction
import com.example.savepost.presentation.model.SavePostEvent
import com.example.savepost.presentation.model.SavePostState
import com.example.savepost.usecase.GetContentTypeUseCase
import com.example.savepost.usecase.SavePostUseCase
import com.example.savepost.usecase.ValidatePostFormUseCase
import com.example.ui.base.BaseViewModel
import com.example.ui.model.PostDataUiModel
import com.example.ui.utils.toDomainModel
import com.example.ui.utils.toMediaItem
import kotlinx.coroutines.launch
import javax.inject.Inject

class SavePostViewModel @Inject constructor(
    private val savePostRouter: SavePostRouter,
    private val player: Player,
    private val validatePostFormUseCase: ValidatePostFormUseCase,
    private val savePostUseCase: SavePostUseCase,
    private val getContentTypeUseCase: GetContentTypeUseCase,
    private val exceptionHandlerDelegate: ExceptionHandlerDelegate
) : BaseViewModel<SavePostState, SavePostEvent, SavePostAction>(
    initialState = SavePostState(player = player)
) {

    init { player.prepare() }

    override fun obtainEvent(event: SavePostEvent) {
        when (event) {
            is SavePostEvent.Initiate -> Unit
            is SavePostEvent.BackClick -> savePostRouter.popBackStack()
            is SavePostEvent.TitleValueChange -> _uiState.value =
                _uiState.value.copy(title = event.title, titleError = "")
            is SavePostEvent.ContentValueChange -> onContentValueChanged(event.uri)
            is SavePostEvent.DescriptionValueChange -> _uiState.value =
                    _uiState.value.copy(description = event.description, descriptionError = "")
            is SavePostEvent.RequireSubscriptionChange -> _uiState.value =
                _uiState.value.copy(requiresSubscription = event.requiresSubscription)
            is SavePostEvent.SavePost -> savePost()
        }
    }

    private fun onContentValueChanged(uri: Uri) {
        val contentType = getContentTypeUseCase.invoke(uri)

        _uiState.value =
            _uiState.value.copy(
                content = uri,
                contentType = contentType
            )

        if (contentType == ContentType.VIDEO) {
            player.setMediaItem(_uiState.value.content.toMediaItem())
        }
    }

    private fun savePost() {
        if (validateForm(title = _uiState.value.title, description = _uiState.value.description)) {
            viewModelScope.launch {
                runSuspendCatching(exceptionHandlerDelegate) {
                    savePostUseCase.invoke(
                        PostDataUiModel(
                            title = _uiState.value.title,
                            content = _uiState.value.content.toString(),
                            contentType = _uiState.value.contentType,
                            body = _uiState.value.description,
                            requiresSubscription = _uiState.value.requiresSubscription
                        ).toDomainModel()
                    )
                }.onSuccess {
                    _actionsFlow.emit(SavePostAction.SaveSuccess)
                }.onFailure {
                    _actionsFlow.emit(SavePostAction.SaveError)
                }
                savePostRouter.popBackStack()
            }
        }
    }

    private fun validateForm(
        title: String,
        description: String
    ): Boolean {
        val titleResult = validatePostFormUseCase.validateTitle(title)
        val descriptionResult = validatePostFormUseCase.validateDescription(description)

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

        player.release()
    }
}
