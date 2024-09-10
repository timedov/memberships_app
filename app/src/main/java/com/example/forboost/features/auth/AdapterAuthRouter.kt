package com.example.forboost.features.auth

import com.example.auth.navigation.AuthRouter
import com.example.forboost.navigation.GlobalRouter
import javax.inject.Inject

class AdapterAuthRouter @Inject constructor(
    private val globalRouter: GlobalRouter
) : AuthRouter {

    override fun navigateToMain() {
        globalRouter.navigateToMain()
    }

    override fun navigateToSignUp() {
        TODO("Not yet implemented")
    }

    override fun navigateToForgotPassword() {
        TODO("Not yet implemented")
    }
}