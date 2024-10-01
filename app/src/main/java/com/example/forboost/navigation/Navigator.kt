package com.example.forboost.navigation

import android.util.Log
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

    override fun navigateToMain() {
        Log.d("Navigator", "Imagine it is navigating to main screen")
    }

    override fun navigateToSignUp() {
        TODO("Not yet implemented")
    }

    override fun navigateToForgotPassword() {
        TODO("Not yet implemented")
    }
}