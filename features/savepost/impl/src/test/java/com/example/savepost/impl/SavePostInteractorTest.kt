package com.example.savepost.impl

import com.example.feed.api.domain.model.PostDomainModel
import com.example.feed.api.domain.model.SavePostForm
import com.example.feed.api.domain.repository.PostRepository
import com.example.savepost.api.navigation.SavePostRouter
import com.example.common.utils.Constants
import com.example.savepost.impl.domain.usecase.SavePostInteractor
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class SavePostInteractorTest {
    private val router = mockk<SavePostRouter>(relaxed = true)
    private val postRepository = mockk<PostRepository>(relaxed = true)
    private val dispatcher = Dispatchers.Unconfined
    private val interactor = SavePostInteractor(router, postRepository, dispatcher)

    @Test
    fun `hasPostDraft returns value from repository`() = runBlocking {
        coEvery { postRepository.hasPostDraft() } returns true
        val result = interactor.hasPostDraft()
        assertEquals(true, result)
        coVerify(exactly = 1) { postRepository.hasPostDraft() }
    }

    @Test
    fun `getPostDraft returns value from repository`() = runBlocking {
        val post = PostDomainModel(id = "1", title = "title", content = "content", postedAt = 0L, author = "author", body = "body", requiresSubscription = false)
        coEvery { postRepository.getPostDraft() } returns post
        val result = interactor.getPostDraft()
        assertEquals(post, result)
        coVerify(exactly = 1) { postRepository.getPostDraft() }
    }

    @Test
    fun `validateTitle returns valid result for valid title`() {
        val title = "Valid Title"
        val result = interactor.validateTitle(title)
        assertEquals(true, result.isValid)
    }

    @Test
    fun `validateTitle returns invalid result for short title`() {
        val title = "No"
        val result = interactor.validateTitle(title)
        assertEquals(false, result.isValid)
    }

    @Test
    fun `validateDescription returns valid result for valid description`() {
        val description = "Valid description"
        val result = interactor.validateDescription(description)
        assertEquals(true, result.isValid)
    }

    @Test
    fun `validateDescription returns invalid result for too long description`() {
        val description = "a".repeat(Constants.MAX_POST_DESCRIPTION_LENGTH + 1)
        val result = interactor.validateDescription(description)
        assertEquals(false, result.isValid)
    }

    @Test
    fun `savePostDraft calls repository with correct SavePostForm`() = runBlocking {
        val post = PostDomainModel(id = "1", title = "title", content = "content", postedAt = 1000L, author = "author", body = "body", requiresSubscription = false)
        interactor.savePostDraft(post)
        coVerify(exactly = 1) {
            postRepository.savePostDraft(match<SavePostForm> {
                it.id == post.id &&
                        it.title == post.title &&
                        it.content == post.content &&
                        it.postedAt == post.postedAt &&
                        it.author == post.author &&
                        it.body == post.body &&
                        it.requiresSubscription == post.requiresSubscription
            })
        }
    }

    @Test
    fun `removePostDraft calls repository removePostDraft`() = runBlocking {
        interactor.removePostDraft()
        coVerify(exactly = 1) { postRepository.removePostDraft() }
    }

    @Test
    fun `navigateToProfile calls router navigateToProfile`() {
        interactor.navigateToProfile()
        io.mockk.verify(exactly = 1) { router.navigateToProfile() }
    }

    @Test
    fun `uploadPost calls router runUploadPost`() {
        interactor.uploadPost()
        io.mockk.verify(exactly = 1) { router.runUploadPost() }
    }
}
