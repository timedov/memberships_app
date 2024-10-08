package com.example.postdetails.usecase

import com.example.domain.model.PostStatsDomainModel
import com.example.domain.repository.CommentRepository
import com.example.domain.repository.FavoriteRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetPostStatsByIdUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository,
    private val commentRepository: CommentRepository,
    private val coroutineDispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(id: Long): PostStatsDomainModel =
        withContext(coroutineDispatcher) {
            PostStatsDomainModel(
                id = id,
                favoriteCount = favoriteRepository.getFavoriteCountByPostId(postId = id),
                commentsCount = commentRepository.getCommentCountByPostId(postId = id),
            )
        }
}
