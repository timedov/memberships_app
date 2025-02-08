package com.example.postdetails.impl.domain.usecase

import com.example.postdetails.api.domain.model.CommentDomainModel
import com.example.postdetails.api.domain.repository.CommentRepository
import com.example.postdetails.api.domain.usecase.GetCommentByIdUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetCommentByIdUseCaseImpl @Inject constructor(
    private val commentRepository: CommentRepository,
    private val coroutineDispatcher: CoroutineDispatcher
): GetCommentByIdUseCase {

    override suspend operator fun invoke(id: String): CommentDomainModel =
        withContext(coroutineDispatcher) {
            commentRepository.getCommentById(id)
        }
}
