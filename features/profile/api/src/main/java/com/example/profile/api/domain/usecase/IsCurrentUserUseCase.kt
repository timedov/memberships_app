package com.example.profile.api.domain.usecase

interface IsCurrentUserUseCase {

    suspend operator fun invoke(username: String): Boolean
}