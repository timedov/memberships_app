package com.example.forboost.features.savepost

import com.example.forboost.navigation.GlobalRouter
import com.example.savepost.navigation.SavePostRouter
import javax.inject.Inject

class AdapterSavePostRouter @Inject constructor(
    private val globalRouter: GlobalRouter
) : SavePostRouter {

    override fun popBackStack() {
        globalRouter.popBackStack()
    }
}
