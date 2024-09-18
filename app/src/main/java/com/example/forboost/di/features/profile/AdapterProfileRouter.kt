package com.example.forboost.di.features.profile

import com.example.forboost.navigation.GlobalRouter
import com.example.profile.navigation.ProfileRouter
import javax.inject.Inject

class AdapterProfileRouter @Inject constructor(
    private val globalRouter: GlobalRouter
) : ProfileRouter {
    override fun navigateToPostDetails(id: Long) {
        TODO("Not yet implemented")
    }

    override fun popBackStack() {
        globalRouter.popBackStack()
    }

    override fun navigateToSubscribe() {
        TODO("Not yet implemented")
    }
}