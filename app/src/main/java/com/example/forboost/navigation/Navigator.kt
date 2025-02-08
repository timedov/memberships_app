package com.example.forboost.navigation

import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.example.common.utils.Keys
import com.example.forboost.R
import javax.inject.Inject

class Navigator @Inject constructor() : GlobalRouter {

    private var navController: NavController? = null
    private var appComponentRunner: AppComponentRunner? = null

    fun attachNavController(navController: NavController) {
        this.navController = navController
    }

    fun detachNavController(navController: NavController) {
        if (this.navController == navController) {
            this.navController = null
        }
    }

    fun setAppComponentRunner(appComponentRunner: AppComponentRunner) {
        this.appComponentRunner = appComponentRunner
    }

    fun removeAppComponentRunner() {
        appComponentRunner = null
    }

    override fun popBackStack() {
        navController?.popBackStack()
    }

    override fun navigateToMain() {
        navController?.let { nc ->
            val options = if (nc.currentDestination?.id == R.id.signInFragment) {
                NavOptions.Builder()
                    .setPopUpTo(R.id.signInFragment, inclusive = true)
                    .build()
            } else null
            nc.navigate(R.id.feedFragment, null, options)
        }
    }

    override fun navigateToSignUp() {
        TODO("Not yet implemented")
    }

    override fun navigateToForgotPassword() {
        TODO("Not yet implemented")
    }

    override fun navigateToPostDetails(postId: String) {
        navController?.navigate(
            R.id.postDetailsFragment,
            bundleOf(Keys.POST_ID_KEY to postId)
        )
    }

    override fun navigateToSignIn() {
        navController?.let { nc ->
            val options = nc.currentDestination?.id?.let {
                NavOptions.Builder()
                    .setPopUpTo(it, inclusive = true)
                    .build()
            }
            nc.navigate(R.id.signInFragment, null, options)
        }
    }

    override fun navigateToProfile(username: String) {
        navController?.let { nc ->
            if (nc.currentDestination?.id == R.id.savePostFragment) {
                val options = NavOptions.Builder()
                    .setPopUpTo(R.id.savePostFragment, inclusive = true)
                    .build()
                nc.navigate(
                    R.id.profileFragment,
                    bundleOf(Keys.USERNAME_KEY to username),
                    options
                )
            } else {
                nc.navigate(R.id.profileFragment, bundleOf(Keys.USERNAME_KEY to username))
            }
        }
    }

    override fun navigateToCommentReplies(parentCommentId: String) {
        navController?.navigate(
            R.id.commentRepliesFragment,
            bundleOf(Keys.PARENT_COMMENT_ID_KEY to parentCommentId)
        )
    }

    override fun runUploadPost() {
        appComponentRunner?.runUploadPostService()
    }
}
