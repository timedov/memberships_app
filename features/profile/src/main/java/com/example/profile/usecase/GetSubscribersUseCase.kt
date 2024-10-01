package com.example.profile.usecase

import com.example.domain.repository.UserRepository
import com.example.ui.utils.subscribersCountToPrettyFormat
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetSubscribersUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val coroutineDispatcher: CoroutineDispatcher
) {

    suspend fun invoke(username: String): String =
        withContext(coroutineDispatcher) {
            userRepository.getUserSubscribersCount(username).subscribersCountToPrettyFormat()
        }
}