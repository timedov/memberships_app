package com.example.postdetails.navigation

interface PostDetailsRouter {

    fun popBackStack()

    fun navigateToProfile(username: String)

    fun navigateToCommentReplies(commentId: String)

    fun navigateToSubscribe(username: String)
}
