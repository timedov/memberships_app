package com.example.postdetails.impl

import androidx.paging.PagingData
import com.example.postdetails.api.domain.model.CommentDomainModel
import com.example.postdetails.api.domain.repository.CommentRepository
import com.example.postdetails.impl.domain.usecase.GetCommentRepliesUseCaseImpl
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class GetCommentRepliesUseCaseImplTest {
    @Test
    fun `invoke returns comment replies flow from repository`() = runBlocking {
        val commentRepository = mockk<CommentRepository>()
        val pagingData = PagingData.empty<CommentDomainModel>()
        every { commentRepository.getCommentsByParentCommentId("parent1") } returns flowOf(pagingData)
        val useCase = GetCommentRepliesUseCaseImpl(commentRepository)
        val result = useCase("parent1").first()
        assertEquals(pagingData, result)
    }
}
