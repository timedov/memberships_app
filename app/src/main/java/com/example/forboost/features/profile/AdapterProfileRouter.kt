package com.example.forboost.features.profile

import com.example.forboost.navigation.GlobalRouter
import com.example.profile.api.navigation.ProfileRouter
import javax.inject.Inject

class AdapterProfileRouter @Inject constructor(
    private val globalRouter: GlobalRouter
) : ProfileRouter {
    override fun navigateToPostDetails(id: String) {
        globalRouter.navigateToPostDetails(id)
    }

    override fun navigateToSignIn() {
        globalRouter.navigateToSignIn()
    }

    override fun popBackStack() {
        globalRouter.popBackStack()
    }
}