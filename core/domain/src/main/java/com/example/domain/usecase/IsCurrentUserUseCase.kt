package com.example.domain.usecase

import com.example.domain.repository.UserRepository
import javax.inject.Inject

class IsCurrentUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(username: String): Boolean =
        userRepository.getCurrentUserCredentials() == username
}