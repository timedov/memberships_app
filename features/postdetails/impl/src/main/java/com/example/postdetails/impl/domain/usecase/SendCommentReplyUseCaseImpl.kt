package com.example.postdetails.impl.domain.usecase

import com.example.postdetails.api.domain.model.CommentDomainModel
import com.example.postdetails.api.domain.repository.CommentRepository
import com.example.postdetails.api.domain.usecase.SendCommentReplyUseCase
import com.example.profile.api.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SendCommentReplyUseCaseImpl @Inject constructor(
    private val commentRepository: CommentRepository,
    private val userRepository: UserRepository,
    private val coroutineDispatcher: CoroutineDispatcher,
): SendCommentReplyUseCase {

    override suspend operator fun invoke(parentCommentId: String, comment: String) {
        withContext(coroutineDispatcher) {
            commentRepository.addComment(
                comment = CommentDomainModel(
                    parentCommentId = parentCommentId,
                    username = userRepository.getCurrentUserCredentials(),
                    postedAt = System.currentTimeMillis(),
                    body = comment,
                )
            )
        }
    }
}
