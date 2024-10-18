package com.example.forboost.features.profile

import com.example.forboost.navigation.GlobalRouter
import com.example.profile.navigation.ProfileRouter
import javax.inject.Inject

class AdapterProfileRouter @Inject constructor(
    private val globalRouter: GlobalRouter
) : ProfileRouter {
    override fun navigateToPostDetails(id: Long) {
        globalRouter.navigateToPostDetails(id)
    }

    override fun popBackStack() {
        globalRouter.popBackStack()
    }

    override fun navigateToSubscribe(username: String) {
        globalRouter.navigateToSubscribe(username)
    }
}