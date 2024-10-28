package com.example.domain.usecase

import com.example.domain.repository.SubscribeRepository
import com.example.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class IsUserSubscribedUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val subscribeRepository: SubscribeRepository,
    private val coroutineDispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(username: String): Boolean =
        withContext(coroutineDispatcher) {
            subscribeRepository.isUserSubscribed(
                followed = userRepository.getCurrentUserCredentials(),
                subscribedTo = username
            )
        }
}
