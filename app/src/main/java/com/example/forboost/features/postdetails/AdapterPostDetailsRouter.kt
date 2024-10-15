package com.example.forboost.features.postdetails

import com.example.forboost.navigation.GlobalRouter
import com.example.postdetails.navigation.PostDetailsRouter
import javax.inject.Inject

class AdapterPostDetailsRouter @Inject constructor(
    private val globalRouter: GlobalRouter
) : PostDetailsRouter {

    override fun popBackStack() {
        globalRouter.popBackStack()
    }

    override fun navigateToProfile(username: String) {
        globalRouter.navigateToProfile(username)
    }

    override fun navigateToCommentReplies(commentId: String) {
        globalRouter.navigateToCommentReplies(commentId)
    }

    override fun navigateToSubscribe(username: String) {
        globalRouter.navigateToSubscribe(username)
    }
}
