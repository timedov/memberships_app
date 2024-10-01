package com.example.forboost.navigation

import androidx.navigation.NavController
import javax.inject.Inject

class Navigator @Inject constructor() : GlobalRouter {

    private var navController: NavController? = null

    fun attachNavController(navController: NavController) {
        this.navController = navController
    }

    fun detachNavController(navController: NavController) {
        if (this.navController == navController) {
            this.navController = null
        }
    }

    override fun popBackStack() {
        navController?.popBackStack()
    }

    override fun navigateToMain() {
        TODO("Not yet implemented")
    }

    override fun navigateToSignUp() {
        TODO("Not yet implemented")
    }

    override fun navigateToForgotPassword() {
        TODO("Not yet implemented")
    }

    override fun navigateToPostDetails(id: Long) {
        TODO("Not yet implemented")
    }

    override fun navigateToSubscribe() {
        TODO("Not yet implemented")
    }
}