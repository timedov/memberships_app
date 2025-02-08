package com.example.postdetails.api.navigation

interface PostDetailsRouter {

    fun popBackStack()

    fun navigateToProfile(username: String)

    fun navigateToCommentReplies(commentId: String)
}
