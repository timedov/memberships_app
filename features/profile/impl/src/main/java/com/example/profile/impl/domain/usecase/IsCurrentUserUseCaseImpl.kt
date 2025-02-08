package com.example.profile.impl.domain.usecase

import com.example.profile.api.domain.repository.UserRepository
import com.example.profile.api.domain.usecase.IsCurrentUserUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class IsCurrentUserUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository,
    private val coroutineDispatcher: CoroutineDispatcher
): IsCurrentUserUseCase {

    override suspend operator fun invoke(username: String): Boolean =
        withContext(coroutineDispatcher) {
            userRepository.getCurrentUserCredentials() == username
        }
}