package com.example.forboost.features.signin

import com.example.signin.api.navigation.SignInRouter
import com.example.forboost.navigation.GlobalRouter
import javax.inject.Inject

class AdapterSignInRouter @Inject constructor(
    private val globalRouter: GlobalRouter
) : SignInRouter {

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