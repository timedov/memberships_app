package com.example.forboost.navigation

interface GlobalRouter {

    fun popBackStack()

    fun navigateToMain()

    fun navigateToSignUp()

    fun navigateToForgotPassword()

    fun navigateToPostDetails(postId: Long)

    fun navigateToSubscribe(username: String)

    fun navigateToSaveTier(tierId: Long)

    fun navigateToProfile(username: String)

    fun navigateToCommentReplies(parentCommentId: String)
}