package com.example.forboost.di.features.savetier

import com.example.forboost.navigation.GlobalRouter
import com.example.savetier.navigation.SaveTierRouter
import javax.inject.Inject

class AdapterSaveTierRouter @Inject constructor(
    private val globalRouter: GlobalRouter
) : SaveTierRouter {

    override fun popBackStack() {
        globalRouter.popBackStack()
    }
}