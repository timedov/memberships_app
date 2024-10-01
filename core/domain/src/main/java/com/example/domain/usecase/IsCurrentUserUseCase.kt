package com.example.domain.usecase

import com.example.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class IsCurrentUserUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val coroutineDispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(username: String): Boolean =
        withContext(coroutineDispatcher) {
            userRepository.getCurrentUserCredentials() == username
        }
}