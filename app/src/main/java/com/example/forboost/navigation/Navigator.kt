package com.example.forboost.navigation

import androidx.core.os.bundleOf
import androidx.navigation.NavController
import com.example.common.utils.Keys
import com.example.forboost.R
import javax.inject.Inject

class Navigator @Inject constructor() : GlobalRouter {

    private var navController: NavController? = null

    fun attachNavController(navController: NavController) {
        this.navController = navController
    }

    fun detachNavController(navController: NavController) {
        if (this.navController == navController) {
            this.navController = null
        }
    }

    override fun popBackStack() {
        navController?.popBackStack()
    }

    override fun navigateToMain() {
        navController?.navigate(R.id.feedFragment)
    }

    override fun navigateToSignUp() {
        TODO("Not yet implemented")
    }

    override fun navigateToForgotPassword() {
        TODO("Not yet implemented")
    }

    override fun navigateToPostDetails(postId: Long) {
        navController?.navigate(
            R.id.postDetailsFragment,
            bundleOf(Keys.POST_ID_KEY to postId)
        )
    }

    override fun navigateToSubscribe(username: String) {
        navController?.navigate(
            R.id.subscribeFragment,
            bundleOf(Keys.USERNAME_KEY to username)
        )
    }

    override fun navigateToSaveTier(tierId: Long) {
        navController?.navigate(
            R.id.saveTierFragment,
            bundleOf(Keys.TIER_ID_KEY to tierId)
        )
    }

    override fun navigateToProfile(username: String) {
        navController?.navigate(
            R.id.profileFragment,
            bundleOf(Keys.USERNAME_KEY to username)
        )
    }

    override fun navigateToCommentReplies(parentCommentId: String) {
        navController?.navigate(
            R.id.commentRepliesFragment,
            bundleOf(Keys.PARENT_COMMENT_ID_KEY to parentCommentId)
        )
    }
}
