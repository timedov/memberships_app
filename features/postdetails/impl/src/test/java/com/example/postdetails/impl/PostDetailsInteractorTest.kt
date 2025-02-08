package com.example.postdetails.impl

import androidx.paging.PagingData
import com.example.feed.api.domain.model.PostDomainModel
import com.example.postdetails.api.domain.model.CommentDomainModel
import com.example.postdetails.api.domain.repository.CommentRepository
import com.example.feed.api.domain.repository.PostRepository
import com.example.profile.api.domain.model.UserDomainModel
import com.example.profile.api.domain.repository.SubscribeRepository
import com.example.profile.api.domain.repository.UserRepository
import com.example.postdetails.api.domain.repository.FavoriteRepository
import com.example.postdetails.impl.domain.usecase.PostDetailsInteractor
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class PostDetailsInteractorTest {
    private val postRepository = mockk<PostRepository>()
    private val userRepository = mockk<UserRepository>()
    private val subscribeRepository = mockk<SubscribeRepository>()
    private val favoriteRepository = mockk<FavoriteRepository>(relaxed = true)
    private val commentRepository = mockk<CommentRepository>(relaxed = true)
    private val dispatcher = Dispatchers.Unconfined
    private val interactor = PostDetailsInteractor(
        postRepository,
        userRepository,
        subscribeRepository,
        favoriteRepository,
        commentRepository,
        dispatcher
    )

    @Test
    fun `getPostById returns post from repository`() = runBlocking {
        val post = PostDomainModel(id = "post1", title = "Title", content = "Content")
        coEvery { postRepository.getPostById("post1") } returns post
        val result = interactor.getPostById("post1")
        assertEquals(post, result)
        coVerify(exactly = 1) { postRepository.getPostById("post1") }
    }

    @Test
    fun `getUserDetails returns user details from repository`() = runBlocking {
        val user = UserDomainModel(username = "user1")
        coEvery { userRepository.getUserByUsername("user1") } returns user
        val result = interactor.getUserDetails("user1")
        assertEquals(user, result)
        coVerify(exactly = 1) { userRepository.getUserByUsername("user1") }
    }

    @Test
    fun `getPostStatsById returns correct stats`() = runBlocking {
        coEvery { favoriteRepository.getFavoriteCountByPostId("post1") } returns 5
        coEvery { commentRepository.getCommentCountByPostId("post1") } returns 3
        val stats = interactor.getPostStatsById("post1")
        assertEquals("post1", stats.id)
        assertEquals(5, stats.favoriteCount)
        assertEquals(3, stats.commentsCount)
    }

    @Test
    fun `isPostFavorite returns true when post is favorite`() = runBlocking {
        coEvery { userRepository.getCurrentUserCredentials() } returns "user1"
        coEvery { favoriteRepository.isPostFavorite("post1", "user1") } returns true
        val result = interactor.isPostFavorite("post1")
        assertTrue(result)
        coVerify(exactly = 1) { favoriteRepository.isPostFavorite("post1", "user1") }
    }

    @Test
    fun `getComments returns comments flow from repository`() = runBlocking {
        val pagingData = PagingData.empty<CommentDomainModel>()
        every { commentRepository.getCommentsByPostId("post1") } returns flowOf(pagingData)
        val result = interactor.getComments("post1").first()
        assertEquals(pagingData, result)
    }

    @Test
    fun `setPostFavorite calls favoriteRepository with correct favorite model`() = runBlocking {
        coEvery { userRepository.getCurrentUserCredentials() } returns "user1"
        interactor.setPostFavorite("post1", true)
        coVerify(exactly = 1) { favoriteRepository.setPostFavorite(match { it.postId == "post1" && it.username == "user1" && it.isFavorite }) }
    }

    @Test
    fun `sendComment calls commentRepository with correct comment`() = runBlocking {
        coEvery { userRepository.getCurrentUserCredentials() } returns "user1"
        interactor.sendComment("post1", "Nice post")
        coVerify(exactly = 1) { commentRepository.addComment(match { it.postId == "post1" && it.username == "user1" && it.body == "Nice post" }) }
    }
}
