package com.example.forboost.features.subscribe

import com.example.forboost.navigation.GlobalRouter
import com.example.subscribe.navigation.SubscribeRouter
import javax.inject.Inject

class AdapterSubscribeRouter @Inject constructor(
    private val globalRouter: GlobalRouter
) : SubscribeRouter {

    override fun popBackStack() {
        globalRouter.popBackStack()
    }

    override fun navigateToSaveTier(id: Long) {
        globalRouter.navigateToSaveTier(id)
    }

}