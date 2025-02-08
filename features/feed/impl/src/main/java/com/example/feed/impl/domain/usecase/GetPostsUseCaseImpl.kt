package com.example.feed.impl.domain.usecase

import androidx.paging.PagingData
import com.example.feed.api.domain.model.PostDomainModel
import com.example.feed.api.domain.repository.PostRepository
import com.example.feed.api.domain.usecase.GetPostsUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPostsUseCaseImpl @Inject constructor(
    private val postRepository: PostRepository
) : GetPostsUseCase {

    override operator fun invoke(): Flow<PagingData<PostDomainModel>> =
        postRepository.getPosts()
}