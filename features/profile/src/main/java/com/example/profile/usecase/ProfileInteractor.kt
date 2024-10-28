package com.example.profile.usecase

import androidx.paging.PagingData
import com.example.domain.model.PostDomainModel
import com.example.domain.model.UserDetailsDomainModel
import com.example.domain.usecase.GetCurrentUsernameUseCase
import com.example.domain.usecase.GetUserDetailsUseCase
import com.example.domain.usecase.IsCurrentUserUseCase
import com.example.domain.usecase.IsUserSubscribedUseCase
import com.example.profile.navigation.ProfileRouter
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProfileInteractor @Inject constructor(
    private val profileRouter: ProfileRouter,
    private val getCurrentUsernameUseCase: GetCurrentUsernameUseCase,
    private val isCurrentUserUseCase: IsCurrentUserUseCase,
    private val isUserSubscribedUseCase: IsUserSubscribedUseCase,
    private val getUserDetailsUseCase: GetUserDetailsUseCase,
    private val getSubscribersUseCase: GetSubscribersUseCase,
    private val getPostsOfUserUseCase: GetPostsOfUserUseCase
) {

    suspend fun getCurrentUsername(): String = getCurrentUsernameUseCase.invoke()

    suspend fun isCurrentUser(username: String): Boolean =
        isCurrentUserUseCase.invoke(username)

    suspend fun isUserSubscribed(username: String): Boolean =
        isUserSubscribedUseCase.invoke(username)

    suspend fun getUserDetails(username: String): UserDetailsDomainModel =
        getUserDetailsUseCase.invoke(username)

    suspend fun getSubscribersCount(username: String): Int =
        getSubscribersUseCase.invoke(username)

    fun getUserPosts(username: String): Flow<PagingData<PostDomainModel>> =
        getPostsOfUserUseCase.invoke(username)

    fun navigateToSubscribeScreen(username: String) {
        profileRouter.navigateToSubscribe(username)
    }

    fun navigateToPostDetailsScreen(postId: Long) {
        profileRouter.navigateToPostDetails(postId)
    }

    fun popBackStack() {
        profileRouter.popBackStack()
    }
}
