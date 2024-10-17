package com.example.auth.presentation.login

import androidx.lifecycle.viewModelScope
import com.example.auth.navigation.AuthRouter
import com.example.auth.presentation.login.model.LoginAction
import com.example.auth.presentation.login.model.LoginEvent
import com.example.auth.presentation.login.model.LoginState
import com.example.common.exceptions.AppException
import com.example.common.utils.ExceptionHandlerDelegate
import com.example.common.utils.runSuspendCatching
import com.example.domain.repository.UserRepository
import com.example.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val router: AuthRouter,
    private val userRepository: UserRepository,
    private val exceptionHandlerDelegate: ExceptionHandlerDelegate
) : BaseViewModel<LoginState, LoginEvent, LoginAction>(
    initialState = LoginState()
) {

    init {
        checkUserAuthorized()
    }

    override fun obtainEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.LogInClick -> logIn()
            is LoginEvent.ForgotPasswordClick -> navigateToForgotPasswordScreen()
            is LoginEvent.SignUpClick -> navigateToSignUp()
            is LoginEvent.EmailChanged -> onEmailChanged(event.email)
            is LoginEvent.PasswordChanged -> onPasswordChanged(event.password)
        }
    }

    private fun checkUserAuthorized() {
        viewModelScope.launch {
            runSuspendCatching(exceptionHandlerDelegate) {
                userRepository.getCurrentUserId()
            }.onSuccess {
                if (!it.isNullOrEmpty()) navigateToHomeScreen()
            }
        }
    }

    private fun logIn() {
        _uiState.value = _uiState.value.copy(isLoading = true)

        viewModelScope.launch {
            runSuspendCatching(exceptionHandlerDelegate) {
                userRepository.signIn(_uiState.value.email, _uiState.value.password)
            }.onSuccess {
                _uiState.value = _uiState.value.copy(isLoading = false)

                navigateToHomeScreen()
            }.onFailure {
                _uiState.value = _uiState.value.copy(isLoading = false)

                if (it is AppException.AuthInvalidCredentialsException) {
                    _uiState.value = _uiState.value.copy(isInvalidCredentials = true)
                }

                _actionsFlow.emit(LoginAction.ShowMessage(it.message.orEmpty()))
            }
        }
    }

    private fun onEmailChanged(email: String) {
        _uiState.value = _uiState.value.copy(email = email, isInvalidCredentials = false)
    }

    private fun onPasswordChanged(password: String) {
        _uiState.value = _uiState.value.copy(password = password, isInvalidCredentials = false)
    }

    private fun navigateToForgotPasswordScreen() {
        router.navigateToForgotPassword()
    }

    private fun navigateToSignUp() {
        router.navigateToSignUp()
    }

    private fun navigateToHomeScreen() {
        router.navigateToFeed()
    }
}
