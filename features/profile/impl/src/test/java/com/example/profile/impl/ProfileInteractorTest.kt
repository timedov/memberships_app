package com.example.profile.impl

import androidx.paging.PagingData
import com.example.feed.api.domain.model.PostDomainModel
import com.example.profile.api.domain.model.UserDomainModel
import com.example.profile.api.domain.repository.SubscribeRepository
import com.example.profile.api.domain.repository.UserRepository
import com.example.profile.api.navigation.ProfileRouter
import com.example.data.api.user.datasource.AuthService
import com.example.feed.api.domain.repository.PostRepository
import com.example.profile.impl.domain.usecase.ProfileInteractor
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ProfileInteractorTest {
    private val router = mockk<ProfileRouter>(relaxed = true)
    private val authService = mockk<AuthService>(relaxed = true)
    private val userRepository = mockk<UserRepository>()
    private val subscribeRepository = mockk<SubscribeRepository>()
    private val postRepository = mockk<PostRepository>()
    private val dispatcher = Dispatchers.Unconfined
    private val interactor = ProfileInteractor(router, authService, userRepository, subscribeRepository, postRepository, dispatcher)

    @Test
    fun `getCurrentUsername returns username from repository`() = runBlocking {
        val username = "testUser"
        coEvery { userRepository.getCurrentUserCredentials() } returns username
        val result = interactor.getCurrentUsername()
        assertEquals(username, result)
        coVerify(exactly = 1) { userRepository.getCurrentUserCredentials() }
    }

    @Test
    fun `isCurrentUser returns true when usernames match`() = runBlocking {
        val username = "testUser"
        coEvery { userRepository.getCurrentUserCredentials() } returns username
        val result = interactor.isCurrentUser(username)
        assertTrue(result)
        coVerify(exactly = 1) { userRepository.getCurrentUserCredentials() }
    }

    @Test
    fun `isCurrentUser returns false when usernames do not match`() = runBlocking {
        coEvery { userRepository.getCurrentUserCredentials() } returns "otherUser"
        val result = interactor.isCurrentUser("testUser")
        assertFalse(result)
        coVerify(exactly = 1) { userRepository.getCurrentUserCredentials() }
    }

    @Test
    fun `isUserSubscribed returns value from subscribeRepository`() = runBlocking {
        val currentUsername = "currentUser"
        val subscribedTo = "otherUser"
        coEvery { userRepository.getCurrentUserCredentials() } returns currentUsername
        coEvery { subscribeRepository.isUserSubscribed(subscribedTo = currentUsername, followed = subscribedTo) } returns true
        val result = interactor.isUserSubscribed(subscribedTo)
        assertTrue(result)
        coVerify(exactly = 1) { subscribeRepository.isUserSubscribed(subscribedTo = currentUsername, followed = subscribedTo) }
    }

    @Test
    fun `getUserDetails returns user details from repository`() = runBlocking {
        val username = "testUser"
        val user = UserDomainModel(username = username)
        coEvery { userRepository.getUserByUsername(username) } returns user
        val result = interactor.getUserDetails(username)
        assertEquals(user, result)
        coVerify(exactly = 1) { userRepository.getUserByUsername(username) }
    }

    @Test
    fun `getSubscribersCount returns count from subscribeRepository`() = runBlocking {
        val username = "testUser"
        val count = 10
        coEvery { subscribeRepository.getUserSubscribersCount(username) } returns count
        val result = interactor.getSubscribersCount(username)
        assertEquals(count, result)
        coVerify(exactly = 1) { subscribeRepository.getUserSubscribersCount(username) }
    }

    @Test
    fun `getUserPosts returns posts flow from postRepository`() = runBlocking {
        val username = "testUser"
        val pagingData = PagingData.empty<PostDomainModel>()
        every { postRepository.getPostsOfUser(username) } returns flowOf(pagingData)
        val flow = interactor.getUserPosts(username)
        val result = flow.first()
        assertEquals(pagingData, result)
    }

    @Test
    fun `signOut calls authService signOut`() = runBlocking {
        interactor.signOut()
        coVerify(exactly = 1) { authService.signOut() }
    }

    @Test
    fun `subscribe unsubscribes when already subscribed and returns false`() = runBlocking {
        val currentUsername = "currentUser"
        val subscribedTo = "otherUser"
        coEvery { userRepository.getCurrentUserCredentials() } returns currentUsername
        coEvery { subscribeRepository.isUserSubscribed(subscribedTo = currentUsername, followed = subscribedTo) } returns true
        coEvery { subscribeRepository.unsubscribeFromUser(subscribedTo = currentUsername, followed = subscribedTo) } returns Unit
        val result = interactor.subscribe(subscribedTo)
        assertFalse(result)
        coVerify(exactly = 1) { subscribeRepository.unsubscribeFromUser(subscribedTo = currentUsername, followed = subscribedTo) }
    }

    @Test
    fun `subscribe subscribes when not subscribed and returns true`() = runBlocking {
        val currentUsername = "currentUser"
        val subscribedTo = "otherUser"
        coEvery { userRepository.getCurrentUserCredentials() } returns currentUsername
        coEvery { subscribeRepository.isUserSubscribed(subscribedTo = currentUsername, followed = subscribedTo) } returns false
        coEvery { subscribeRepository.subscribeToUser(any()) } returns Unit
        val result = interactor.subscribe(subscribedTo)
        assertTrue(result)
        coVerify(exactly = 1) { subscribeRepository.subscribeToUser(match { it.subscribedTo == currentUsername && it.followed == subscribedTo }) }
    }

    @Test
    fun `navigateToPostDetails calls router navigateToPostDetails`() {
        val postId = "123"
        interactor.navigateToPostDetails(postId)
        io.mockk.verify(exactly = 1) { router.navigateToPostDetails(postId) }
    }

    @Test
    fun `navigateToSignIn calls router navigateToSignIn`() {
        interactor.navigateToSignIn()
        io.mockk.verify(exactly = 1) { router.navigateToSignIn() }
    }

    @Test
    fun `popBackStack calls router popBackStack`() {
        interactor.popBackStack()
        io.mockk.verify(exactly = 1) { router.popBackStack() }
    }
}
