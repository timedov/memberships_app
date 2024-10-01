package com.example.profile.usecase

import com.example.domain.repository.UserRepository
import com.example.ui.utils.subscribersCountToPrettyFormat
import javax.inject.Inject

class GetSubscribersUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend fun invoke(username: String): String =
        userRepository.getUserSubscribersCount(username).subscribersCountToPrettyFormat()
}