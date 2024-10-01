package com.example.domain.usecase

import com.example.domain.model.UserDetailsDomainModel
import com.example.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetUserDetailsUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val coroutineDispatcher: CoroutineDispatcher
) {

    suspend fun invoke(username: String): UserDetailsDomainModel =
        withContext(coroutineDispatcher) {
            userRepository.getUserDetailsByUsername(username)
        }
}