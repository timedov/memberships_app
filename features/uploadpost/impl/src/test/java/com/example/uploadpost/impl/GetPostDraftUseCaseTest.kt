package com.example.uploadpost.impl

import com.example.feed.api.domain.model.PostDomainModel
import com.example.feed.api.domain.repository.PostRepository
import com.example.uploadpost.impl.domain.usecase.GetPostDraftUseCaseImpl
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class GetPostDraftUseCaseImplTest {
    @Test
    fun `invoke returns draft from repository`() = runBlocking {
        val postRepository = mockk<PostRepository>()
        val expectedPost = PostDomainModel()
        coEvery { postRepository.getPostDraft() } returns expectedPost
        val useCase = GetPostDraftUseCaseImpl(postRepository)
        val result = useCase()
        coVerify(exactly = 1) { postRepository.getPostDraft() }
        assertEquals(expectedPost, result)
    }
}
