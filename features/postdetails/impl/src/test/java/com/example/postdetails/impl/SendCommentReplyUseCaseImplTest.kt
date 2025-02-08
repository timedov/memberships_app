package com.example.postdetails.impl

import com.example.postdetails.api.domain.repository.CommentRepository
import com.example.postdetails.api.domain.usecase.SendCommentReplyUseCase
import com.example.postdetails.impl.domain.usecase.SendCommentReplyUseCaseImpl
import com.example.profile.api.domain.repository.UserRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Test

class SendCommentReplyUseCaseImplTest {
    @Test
    fun `invoke sends reply comment with correct data`() = runBlocking {
        val commentRepository = mockk<CommentRepository>(relaxed = true)
        val userRepository = mockk<UserRepository>()
        coEvery { userRepository.getCurrentUserCredentials() } returns "user1"
        val useCase: SendCommentReplyUseCase = SendCommentReplyUseCaseImpl(commentRepository, userRepository, Dispatchers.Unconfined)
        useCase("parent1", "Reply comment")
        coVerify(exactly = 1) { commentRepository.addComment(match { it.parentCommentId == "parent1" && it.username == "user1" && it.body == "Reply comment" }) }
    }
}
