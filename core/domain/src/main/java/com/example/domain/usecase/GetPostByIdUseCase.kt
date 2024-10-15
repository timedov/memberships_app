package com.example.domain.usecase

import com.example.domain.model.PostDataDomainModel
import com.example.domain.repository.PostRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetPostByIdUseCase @Inject constructor(
    private val postRepository: PostRepository,
    private val coroutineDispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(id: Long): PostDataDomainModel =
        withContext(coroutineDispatcher) {
            postRepository.getPostById(id = id)
        }
}
