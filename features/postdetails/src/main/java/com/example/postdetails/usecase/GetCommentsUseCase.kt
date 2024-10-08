package com.example.postdetails.usecase

import androidx.paging.PagingData
import com.example.domain.model.CommentDomainModel
import com.example.domain.repository.CommentRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCommentsUseCase @Inject constructor(
    private val commentRepository: CommentRepository,
) {

    operator fun invoke(postId: Long): Flow<PagingData<CommentDomainModel>> =
        commentRepository.getCommentsByPostId(postId = postId)
}