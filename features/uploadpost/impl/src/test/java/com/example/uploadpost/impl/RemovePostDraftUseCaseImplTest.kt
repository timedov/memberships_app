package com.example.uploadpost.impl

import com.example.feed.api.domain.repository.PostRepository
import com.example.uploadpost.impl.domain.usecase.RemovePostDraftUseCaseImpl
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Test

class RemovePostDraftUseCaseImplTest {
    @Test
    fun `invoke calls removePostDraft on repository`() = runBlocking {
        val postRepository = mockk<PostRepository>(relaxed = true)
        val useCase = RemovePostDraftUseCaseImpl(postRepository, Dispatchers.Unconfined)
        useCase.invoke()
        coVerify(exactly = 1) { postRepository.removePostDraft() }
    }
}
