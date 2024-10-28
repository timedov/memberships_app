package com.example.domain.usecase

import com.example.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetCurrentUsernameUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val coroutineDispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(): String =
        withContext(coroutineDispatcher) {
            userRepository.getCurrentUserCredentials()
        }
}
