package com.example.forboost.features.auth

import com.example.auth.navigation.AuthRouter
import com.example.forboost.navigation.GlobalRouter
import javax.inject.Inject

class AdapterAuthRouter @Inject constructor(
    private val globalRouter: GlobalRouter
) : AuthRouter {

    override fun navigateToFeed() {
        globalRouter.navigateToMain()
    }

    override fun navigateToSignUp() {
        globalRouter.navigateToSignUp()
    }

    override fun navigateToForgotPassword() {
        globalRouter.navigateToForgotPassword()
    }
}