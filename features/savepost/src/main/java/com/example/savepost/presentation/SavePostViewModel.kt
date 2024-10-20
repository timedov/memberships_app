package com.example.savepost.presentation

import com.example.common.utils.ExceptionHandlerDelegate
import com.example.savepost.navigation.SavePostRouter
import com.example.savepost.presentation.model.SavePostAction
import com.example.savepost.presentation.model.SavePostEvent
import com.example.savepost.presentation.model.SavePostState
import com.example.ui.base.BaseViewModel
import javax.inject.Inject

class SavePostViewModel @Inject constructor(
    private val savePostRouter: SavePostRouter,
    private val exceptionHandlerDelegate: ExceptionHandlerDelegate
) : BaseViewModel<SavePostState, SavePostEvent, SavePostAction>(
    initialState = SavePostState()
) {

    override fun obtainEvent(event: SavePostEvent) {
        when (event) {
            is SavePostEvent.Initiate -> {}
            is SavePostEvent.BackClick -> savePostRouter.popBackStack()
            is SavePostEvent.TitleValueChange ->
                _uiState.value = _uiState.value.copy(title = event.title)
            is SavePostEvent.DescriptionValueChange ->
                _uiState.value = _uiState.value.copy(description = event.description)
            is SavePostEvent.SavePost -> savePost()
        }
    }

    private fun savePost() {
    }
}
