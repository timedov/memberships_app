package com.example.forboost.navigation

interface GlobalRouter {

    fun popBackStack()

    fun navigateToMain()

    fun navigateToSignUp()

    fun navigateToForgotPassword()

    fun navigateToPostDetails(id: Long)

    // TODO: remove
    fun navigateToSubscribe()

    fun navigateToSubscribe(username: String)

    fun navigateToSaveTier(id: Long)

    fun navigateToProfile(username: String)

    fun navigateToCommentReplies(commentId: String)
}