package com.example.forboost.navigation

import androidx.navigation.NavController

class Navigator : GlobalRouter {

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

    override fun navigateToPostDetails(id: Long) {
        TODO("Not yet implemented")
    }

    override fun navigateToSubscribe() {
        TODO("Not yet implemented")
    }
}