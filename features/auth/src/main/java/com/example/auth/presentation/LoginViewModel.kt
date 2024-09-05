package com.example.auth.presentation

import com.example.auth.presentation.model.LoginAction
import com.example.auth.presentation.model.LoginEvent
import com.example.auth.presentation.model.LoginState
import com.example.common.base.BaseViewModel
import javax.inject.Inject

class LoginViewModel @Inject constructor(

) : BaseViewModel<LoginState, LoginAction, LoginEvent>(
    initialState = LoginState.Initial
) {

}