package com.example.profile.api.navigation

interface ProfileRouter {

    fun navigateToPostDetails(id: String)

    fun navigateToSignIn()

    fun popBackStack()
}