package com.example.postdetails.navigation

interface PostDetailsRouter {

    fun popBackStack()

    fun navigateToProfile(username: String)

    fun navigateToSubscribe(username: String)
}
