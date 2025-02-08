package com.example.postdetails.impl.domain.usecase

import androidx.paging.PagingData
import com.example.postdetails.api.domain.model.CommentDomainModel
import com.example.postdetails.api.domain.repository.CommentRepository
import com.example.postdetails.api.domain.usecase.GetCommentRepliesUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetCommentRepliesUseCaseImpl @Inject constructor(
    private val commentRepository: CommentRepository,
): GetCommentRepliesUseCase {

    override operator fun invoke(parentCommentId: String): Flow<PagingData<CommentDomainModel>> =
        commentRepository.getCommentsByParentCommentId(parentCommentId)
}
