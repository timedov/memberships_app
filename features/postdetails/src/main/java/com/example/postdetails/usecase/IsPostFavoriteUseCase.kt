package com.example.postdetails.usecase

import com.example.domain.repository.FavoriteRepository
import com.example.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class IsPostFavoriteUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository,
    private val userRepository: UserRepository,
    private val coroutineDispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(postId: Long): Boolean =
        withContext(coroutineDispatcher) {
            favoriteRepository.isPostFavorite(
                postId = postId,
                username = userRepository.getCurrentUserCredentials()
            )
        }
}