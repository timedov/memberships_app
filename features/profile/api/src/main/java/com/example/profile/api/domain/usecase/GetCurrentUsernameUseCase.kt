package com.example.profile.api.domain.usecase

interface GetCurrentUsernameUseCase {

    suspend operator fun invoke(): String
}