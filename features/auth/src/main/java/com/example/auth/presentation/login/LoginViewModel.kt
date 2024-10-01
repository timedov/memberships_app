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
    initialState = LoginState.Initial
) {

    override fun obtainEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.LogInClick -> handleLogIn(event.email, event.password)
            is LoginEvent.ForgotPasswordClick -> navigateToForgotPasswordScreen()
            is LoginEvent.SignUpClick -> navigateToSignUp()
        }
    }

    private fun handleLogIn(email: String, password: String) {
        if (email.isBlank() || password.isBlank()) {
            _uiState.value = LoginState.InvalidCredentials
            return
        }

        viewModelScope.launch {
            _uiState.value = LoginState.Loading
            runSuspendCatching(exceptionHandlerDelegate) {
                userRepository.signIn(email, password)
            }.onSuccess {
                _uiState.value = LoginState.Initial
                navigateToHomeScreen()
            }.onFailure { error ->
                when (error) {
                    is AppException.AuthInvalidCredentialsException -> {
                        _uiState.value = LoginState.InvalidCredentials
                    }
                    else -> {
                        _uiState.value = LoginState.Error(error)
                    }
                }
                _actionsFlow.emit(LoginAction.ShowMessage(error.message.toString()))
            }
        }
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
