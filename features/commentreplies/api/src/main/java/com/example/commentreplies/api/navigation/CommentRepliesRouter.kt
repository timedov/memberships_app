package com.example.commentreplies.api.navigation

interface CommentRepliesRouter {

    fun popBackStack()

    fun navigateToProfile(username: String)
}
