package com.example.feed.impl

import androidx.paging.PagingData
import com.example.feed.api.domain.model.PostDomainModel
import com.example.feed.api.domain.repository.PostRepository
import com.example.feed.api.domain.usecase.GetPostsUseCase
import com.example.feed.impl.domain.usecase.GetPostsUseCaseImpl
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class GetPostsUseCaseImplTest {
    @Test
    fun `invoke returns posts flow from repository`() = runBlocking {
        val postRepository = mockk<PostRepository>()
        val pagingData = PagingData.empty<PostDomainModel>()
        every { postRepository.getPosts() } returns flowOf(pagingData)
        val useCase: GetPostsUseCase = GetPostsUseCaseImpl(postRepository)
        val result = useCase().first()
        assertEquals(pagingData, result)
    }
}
