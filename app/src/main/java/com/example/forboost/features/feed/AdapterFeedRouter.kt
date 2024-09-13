package com.example.forboost.features.feed

import com.example.feed.navigation.FeedRouter
import com.example.forboost.navigation.GlobalRouter
import javax.inject.Inject

class AdapterFeedRouter @Inject constructor(
    private val globalRouter: GlobalRouter
) : FeedRouter {
    override fun navigateToDetailsScreen(id: Long) {
        TODO("Not yet implemented")
    }

    override fun navigateToProfile() {
        TODO("Not yet implemented")
    }
}