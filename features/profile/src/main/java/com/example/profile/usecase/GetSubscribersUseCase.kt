package com.example.profile.usecase

import com.example.common.utils.subscribersCountToPrettyFormat
import com.example.domain.repository.UserRepository
import javax.inject.Inject

class GetSubscribersUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend fun invoke(username: String): String =
        userRepository.getUserSubscribersCount(username).subscribersCountToPrettyFormat()
}