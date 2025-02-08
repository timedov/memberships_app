package com.example.profile.impl.domain.usecase

import com.example.profile.api.domain.repository.UserRepository
import com.example.profile.api.domain.usecase.GetCurrentUsernameUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetCurrentUsernameUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository,
    private val coroutineDispatcher: CoroutineDispatcher
): GetCurrentUsernameUseCase {

    override suspend operator fun invoke(): String =
        withContext(coroutineDispatcher) {
            userRepository.getCurrentUserCredentials()
        }
}
