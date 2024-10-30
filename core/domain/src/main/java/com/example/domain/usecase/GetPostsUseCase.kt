package com.example.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.example.domain.model.PostWithStatsDomainModel
import com.example.common.model.TierType
import com.example.domain.repository.CommentRepository
import com.example.domain.repository.PostRepository
import com.example.domain.utils.toPostWithStatsDomainModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(
    private val postRepository: PostRepository,
    private val commentRepository: CommentRepository,
    private val coroutineDispatcher: CoroutineDispatcher
) {

    operator fun invoke(tier: TierType): Flow<PagingData<PostWithStatsDomainModel>> =
        postRepository.getPostsByTier(tier)
            .map { pagingData ->
                pagingData.map { post ->
                    withContext(coroutineDispatcher) {
                        val commentsCount = commentRepository.getCommentCountByPostId(post.id)
                        post.toPostWithStatsDomainModel(commentsCount)
                    }
                }
            }
    }
