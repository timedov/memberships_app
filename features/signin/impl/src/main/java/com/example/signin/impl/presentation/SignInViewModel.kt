package com.example.signin.impl.presentation

import androidx.lifecycle.viewModelScope
import com.example.signin.api.domain.usecase.IsUserAuthorizedUseCase
import com.example.signin.api.domain.usecase.SignInUseCase
import com.example.signin.api.navigation.SignInRouter
import com.example.signin.impl.presentation.model.LoginAction
import com.example.signin.impl.presentation.model.SignInEvent
import com.example.signin.impl.presentation.model.LoginState
import com.example.common.exceptions.AppException
import com.example.common.utils.AppExceptionHandler
import com.example.common.utils.runSuspendCatching
import com.example.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class SignInViewModel @Inject constructor(
    private val router: SignInRouter,
    private val isUserAuthorizedUseCase: IsUserAuthorizedUseCase,
    private val signInUseCase: SignInUseCase,
    private val appExceptionHandler: AppExceptionHandler
) : BaseViewModel<LoginState, SignInEvent, LoginAction>(
    initialState = LoginState()
) {

    init {
        checkUserAuthorized()
    }

    override fun obtainEvent(event: SignInEvent) {
        when (event) {
            is SignInEvent.SignInClick -> signIn()
            is SignInEvent.ForgotPasswordClick -> router.navigateToForgotPassword()
            is SignInEvent.SignUpClick -> router.navigateToSignUp()
            is SignInEvent.EmailChanged -> updateEmail(event.email)
            is SignInEvent.PasswordChanged -> updatePassword(event.password)
        }
    }

    private fun checkUserAuthorized() {
        viewModelScope.launch {
            runSuspendCatching(appExceptionHandler) {
                isUserAuthorizedUseCase.invoke()
            }.onSuccess {
                if (it) router.navigateToFeed()
            }
        }
    }

    private fun signIn() {
        _uiState.value = _uiState.value.copy(isLoading = true)

        viewModelScope.launch {
            runSuspendCatching(appExceptionHandler) {
                signInUseCase.invoke(_uiState.value.email, _uiState.value.password)
            }.onSuccess {
                _uiState.value = _uiState.value.copy(isLoading = false)
                router.navigateToFeed()
            }.onFailure {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    isInvalidCredentials = it is AppException.AuthInvalidCredentialsException
                )
                _actionsFlow.emit(LoginAction.ShowMessage(it.message.orEmpty()))
            }
        }
    }

    private fun updateEmail(email: String) {
        _uiState.value = _uiState.value.copy(email = email, isInvalidCredentials = false)
    }

    private fun updatePassword(password: String) {
        _uiState.value = _uiState.value.copy(password = password, isInvalidCredentials = false)
    }
}
