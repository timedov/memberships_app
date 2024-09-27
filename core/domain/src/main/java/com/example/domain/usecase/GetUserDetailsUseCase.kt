package com.example.domain.usecase

import com.example.domain.model.UserDetailsDomainModel
import com.example.domain.repository.UserRepository
import javax.inject.Inject

class GetUserDetailsUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend fun invoke(username: String): UserDetailsDomainModel {
        return userRepository.getUserDetailsByUsername(username)
    }
}