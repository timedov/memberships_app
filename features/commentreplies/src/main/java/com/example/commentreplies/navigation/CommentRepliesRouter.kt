package com.example.commentreplies.navigation

interface CommentRepliesRouter {

    fun popBackStack()

    fun navigateToProfile(username: String)
}
