package com.example.postdetails.usecase

import com.example.domain.repository.FavoriteRepository
import com.example.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SetPostFavoriteUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val favoriteRepository: FavoriteRepository,
    private val coroutineDispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(postId: Long, isFavorite: Boolean) =
        withContext(coroutineDispatcher) {
            val username = userRepository.getCurrentUserCredentials()

            favoriteRepository
                .setPostFavorite(username = username, postId = postId, isFavorite = isFavorite)
        }
}
