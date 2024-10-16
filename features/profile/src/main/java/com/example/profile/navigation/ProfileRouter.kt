package com.example.profile.navigation

interface ProfileRouter {

    fun navigateToPostDetails(id: Long)

    fun popBackStack()

    fun navigateToSubscribe(username: String)
}