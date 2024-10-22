package com.example.forboost.di.features.commentreplies

import com.example.commentreplies.navigation.CommentRepliesRouter
import com.example.forboost.navigation.GlobalRouter
import javax.inject.Inject

class AdapterCommentRepliesRouter @Inject constructor(
    private val globalRouter: GlobalRouter
) : CommentRepliesRouter {
    override fun popBackStack() {
        globalRouter.popBackStack()
    }

    override fun navigateToProfile(username: String) {
        globalRouter.navigateToProfile(username)
    }
}
