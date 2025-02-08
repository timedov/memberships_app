package com.example.uploadpost.impl

import com.example.feed.api.domain.model.PostDomainModel
import com.example.feed.api.domain.repository.PostRepository
import com.example.profile.api.domain.repository.UserRepository
import com.example.uploadpost.impl.domain.usecase.UploadPostUseCaseImpl
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Test

class UploadPostUseCaseImplTest {
    @Test
    fun `invoke saves post with correct data`() = runBlocking {
        val postRepository = mockk<PostRepository>(relaxed = true)
        val userRepository = mockk<UserRepository>()
        val expectedAuthor = "testUser"
        coEvery { userRepository.getCurrentUserCredentials() } returns expectedAuthor
        val useCase = UploadPostUseCaseImpl(postRepository, userRepository, Dispatchers.Unconfined)
        val post = PostDomainModel(title = "Test Title", content = "Test Content", body = "Test Body", requiresSubscription = false)
        useCase.invoke(post)
        coVerify(exactly = 1) { userRepository.getCurrentUserCredentials() }
        coVerify(exactly = 1) {
            postRepository.savePost(match { form ->
                form.title == post.title &&
                        form.content == post.content &&
                        form.body == post.body &&
                        form.requiresSubscription == post.requiresSubscription &&
                        form.author == expectedAuthor &&
                        form.id.isNotEmpty() &&
                        form.postedAt > 0L
            })
        }
    }
}
