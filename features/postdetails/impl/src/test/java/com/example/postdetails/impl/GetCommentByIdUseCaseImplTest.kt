package com.example.postdetails.impl

import com.example.postdetails.api.domain.model.CommentDomainModel
import com.example.postdetails.api.domain.repository.CommentRepository
import com.example.postdetails.impl.domain.usecase.GetCommentByIdUseCaseImpl
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class GetCommentByIdUseCaseImplTest {
    @Test
    fun `invoke returns comment from repository`() = runBlocking {
        val commentRepository = mockk<CommentRepository>()
        val testComment = CommentDomainModel(id = "1", postId = "post1", username = "user1", postedAt = 1234567890L, body = "Test comment")
        coEvery { commentRepository.getCommentById("1") } returns testComment
        val useCase = GetCommentByIdUseCaseImpl(commentRepository, Dispatchers.Unconfined)
        val result = useCase("1")
        assertEquals(testComment, result)
        coVerify(exactly = 1) { commentRepository.getCommentById("1") }
    }
}
