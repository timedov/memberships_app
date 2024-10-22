package com.example.commentreplies.usecase

import com.example.domain.repository.CommentRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetCommentByIdUseCase @Inject constructor(
    private val commentRepository: CommentRepository,
    private val coroutineDispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(id: String) =
        withContext(coroutineDispatcher) {
            commentRepository.getCommentById(id)
        }
}
