package com.example.signin.impl.presentation.model

internal sealed interface LoginAction {
    data class ShowMessage(val message: String) : LoginAction
}