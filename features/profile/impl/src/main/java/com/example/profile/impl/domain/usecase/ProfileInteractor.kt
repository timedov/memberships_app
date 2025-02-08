package com.example.profile.impl.domain.usecase

import androidx.paging.PagingData
import com.example.data.api.user.datasource.AuthService
import com.example.profile.api.domain.repository.SubscribeRepository
import com.example.profile.api.domain.repository.UserRepository
import com.example.feed.api.domain.model.PostDomainModel
import com.example.profile.api.domain.model.UserDomainModel
import com.example.feed.api.domain.repository.PostRepository
import com.example.profile.api.domain.model.SubscribeDomainModel
import com.example.profile.api.navigation.ProfileRouter
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class ProfileInteractor @Inject constructor(
    private val profileRouter: ProfileRouter,
    private val authService: AuthService,
    private val userRepository: UserRepository,
    private val subscribeRepository: SubscribeRepository,
    private val postRepository: PostRepository,
    private val coroutineDispatcher: CoroutineDispatcher
) {

    suspend fun getCurrentUsername(): String =
        withContext(coroutineDispatcher) {
            userRepository.getCurrentUserCredentials()
    }

    suspend fun isCurrentUser(username: String): Boolean =
        withContext(coroutineDispatcher) {
            userRepository.getCurrentUserCredentials() == username
    }

    suspend fun isUserSubscribed(subscribedTo: String): Boolean =
        withContext(coroutineDispatcher) {
            subscribeRepository.isUserSubscribed(
                subscribedTo = getCurrentUsername(),
                followed = subscribedTo
            )
    }

    suspend fun getUserDetails(username: String): UserDomainModel =
        withContext(coroutineDispatcher) {
            userRepository.getUserByUsername(username)
    }

    suspend fun getSubscribersCount(username: String): Int =
        withContext(coroutineDispatcher) {
            subscribeRepository.getUserSubscribersCount(username)
    }

    fun getUserPosts(username: String): Flow<PagingData<PostDomainModel>> =
        postRepository.getPostsOfUser(username)

    suspend fun signOut() {
        authService.signOut()
    }

    suspend fun subscribe(subscribedTo: String): Boolean =
        withContext(coroutineDispatcher) {
            if (isUserSubscribed(subscribedTo)) {
                subscribeRepository.unsubscribeFromUser(
                    subscribedTo = getCurrentUsername(),
                    followed = subscribedTo
                )
                return@withContext false
            } else {
                subscribeRepository.subscribeToUser(
                    SubscribeDomainModel(
                        subscribedTo = getCurrentUsername(),
                        followed = subscribedTo,
                    )
                )
                return@withContext true
            }
        }

    fun navigateToPostDetails(postId: String) {
        profileRouter.navigateToPostDetails(postId)
    }

    fun navigateToSignIn() {
        profileRouter.navigateToSignIn()
    }

    fun popBackStack() {
        profileRouter.popBackStack()
    }
}