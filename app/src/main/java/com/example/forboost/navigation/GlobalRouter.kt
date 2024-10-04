package com.example.forboost.navigation

interface GlobalRouter {

    fun popBackStack()

    fun navigateToMain()

    fun navigateToSignUp()

    fun navigateToForgotPassword()

    fun navigateToPostDetails(id: Long)

    fun navigateToSubscribe()

    fun navigateToSaveTier(id: Long)

    fun navigateToProfile(username: String)
}