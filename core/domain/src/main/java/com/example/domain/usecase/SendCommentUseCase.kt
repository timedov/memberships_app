package com.example.domain.usecase

import com.example.domain.model.CommentDomainModel
import com.example.domain.repository.CommentRepository
import com.example.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SendCommentUseCase @Inject constructor(
    private val commentRepository: CommentRepository,
    private val userRepository: UserRepository,
    private val coroutineDispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(postId: Long, comment: String) {
        withContext(coroutineDispatcher) {
            commentRepository.addComment(
                comment = CommentDomainModel(
                    postId = postId,
                    username = userRepository.getCurrentUserCredentials(),
                    postedAt = System.currentTimeMillis(),
                    text = comment
                )
            )
        }
    }
}