package com.example.postdetails.impl.domain.usecase

import androidx.paging.PagingData
import com.example.feed.api.domain.model.PostDomainModel
import com.example.feed.api.domain.repository.PostRepository
import com.example.postdetails.api.domain.model.CommentDomainModel
import com.example.postdetails.api.domain.model.FavoriteDomainModel
import com.example.postdetails.api.domain.model.PostStatsDomainModel
import com.example.postdetails.api.domain.repository.CommentRepository
import com.example.postdetails.api.domain.repository.FavoriteRepository
import com.example.profile.api.domain.model.UserDomainModel
import com.example.profile.api.domain.repository.SubscribeRepository
import com.example.profile.api.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class PostDetailsInteractor @Inject constructor(
    private val postRepository: PostRepository,
    private val userRepository: UserRepository,
    private val subscribeRepository: SubscribeRepository,
    private val favoriteRepository: FavoriteRepository,
    private val commentRepository: CommentRepository,
    private val coroutineDispatcher: CoroutineDispatcher
) {

    suspend fun getPostById(postId: String): PostDomainModel =
        withContext(coroutineDispatcher) {
            postRepository.getPostById(id = postId)
        }

    suspend fun getUserDetails(username: String): UserDomainModel =
        withContext(coroutineDispatcher) {
            userRepository.getUserByUsername(username = username)
        }

    suspend fun getPostStatsById(postId: String): PostStatsDomainModel =
        withContext(coroutineDispatcher) {
            PostStatsDomainModel(
                id = postId,
                favoriteCount = favoriteRepository.getFavoriteCountByPostId(postId = postId),
                commentsCount = commentRepository.getCommentCountByPostId(postId = postId),
            )
        }

    suspend fun isCurrentUserSubscribed(subscribedTo: String): Boolean =
        withContext(coroutineDispatcher) {
            subscribeRepository.isUserSubscribed(
                followed = userRepository.getCurrentUserCredentials(),
                subscribedTo = subscribedTo
            )
        }

    suspend fun isPostFavorite(postId: String): Boolean =
        withContext(coroutineDispatcher) {
            favoriteRepository.isPostFavorite(
                postId = postId,
                username = userRepository.getCurrentUserCredentials()
            )
        }

    fun getComments(postId: String): Flow<PagingData<CommentDomainModel>> =
        commentRepository.getCommentsByPostId(postId = postId)

    suspend fun setPostFavorite(postId: String, isFavorite: Boolean) {
        withContext(coroutineDispatcher) {
            val username = userRepository.getCurrentUserCredentials()

            favoriteRepository.setPostFavorite(
                favorite = FavoriteDomainModel(
                    postId = postId,
                    username = username,
                    isFavorite = isFavorite
                )
            )
        }
    }

    suspend fun sendComment(postId: String, comment: String) {
        withContext(coroutineDispatcher) {
            commentRepository.addComment(
                comment = CommentDomainModel(
                    postId = postId,
                    username = userRepository.getCurrentUserCredentials(),
                    postedAt = System.currentTimeMillis(),
                    body = comment
                )
            )
        }
    }
}
