package com.example.forboost.features.feed

import com.example.feed.api.navigation.FeedRouter
import com.example.forboost.navigation.GlobalRouter
import javax.inject.Inject

class AdapterFeedRouter @Inject constructor(
    private val globalRouter: GlobalRouter
) : FeedRouter {
    override fun navigateToDetailsScreen(id: String) {
        globalRouter.navigateToPostDetails(id)
    }

    override fun navigateToProfile(username: String) {
        globalRouter.navigateToProfile(username)
    }
}