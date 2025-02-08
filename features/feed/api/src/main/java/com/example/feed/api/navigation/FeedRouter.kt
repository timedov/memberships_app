package com.example.feed.api.navigation

interface FeedRouter {

    fun navigateToDetailsScreen(id: String)

    fun navigateToProfile(username: String)
}