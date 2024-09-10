package com.example.auth.presentation.login

import androidx.lifecycle.viewModelScope
import com.example.auth.navigation.AuthRouter
import com.example.auth.presentation.login.model.LoginAction
import com.example.auth.presentation.login.model.LoginEvent
import com.example.auth.presentation.login.model.LoginState
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
        }
    }

    private fun handleLogIn(email: String, password: String) {
        if (email.isBlank() || password.isBlank()) {
            _uiState.value = LoginState.Error(IllegalArgumentException("Fields cannot be empty"))
            return
        }

        viewModelScope.launch {
            _uiState.value = LoginState.Loading
            runSuspendCatching(exceptionHandlerDelegate) {
                userRepository.signIn(email, password)
            }.onSuccess {
                _uiState.value = LoginState.Initial
                _actionsFlow.emit(LoginAction.NavigateToHomeScreen)
            }.onFailure { error ->
                when {
                    error.message?.contains("The email address is badly formatted") == true -> {
                        _uiState.value = LoginState.NotValidEmail
                    }
                    error.message?.contains("User not found") == true -> {
                        _uiState.value = LoginState.UserNotFound
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

    fun navigateToHomeScreen() {
        router.navigateToMain()
    }
}
