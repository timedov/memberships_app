package com.example.forboost.navigation

interface GlobalRouter {

    fun popBackStack()

    fun navigateToPostDetails(id: Long)

    fun navigateToSubscribe()
}