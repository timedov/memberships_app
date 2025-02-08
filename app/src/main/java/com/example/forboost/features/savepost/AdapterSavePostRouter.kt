package com.example.forboost.features.savepost

import com.example.forboost.navigation.GlobalRouter
import com.example.savepost.api.navigation.SavePostRouter
import javax.inject.Inject

class AdapterSavePostRouter @Inject constructor(
    private val globalRouter: GlobalRouter
) : SavePostRouter {

    override fun runUploadPost() {
        globalRouter.runUploadPost()
    }

    override fun navigateToProfile() {
        globalRouter.navigateToProfile("")
    }
}
