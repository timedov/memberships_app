package com.example.profile.usecase

import com.example.domain.model.UserDetailsModel
import com.example.domain.repository.UserRepository
import javax.inject.Inject

class GetUserDetailsUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

     suspend fun invoke(username: String): UserDetailsModel =
         userRepository.getUserDetailsByUsername(username)
}