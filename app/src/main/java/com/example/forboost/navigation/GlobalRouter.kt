package com.example.forboost.navigation

interface GlobalRouter {

    fun popBackStack()

    fun navigateToMain()

    fun navigateToSignUp()

    fun navigateToForgotPassword()

    fun navigateToPostDetails(postId: String)

    fun navigateToSignIn()

    fun navigateToProfile(username: String)

    fun navigateToCommentReplies(parentCommentId: String)

    fun runUploadPost()
}