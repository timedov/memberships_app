package com.example.signin.api.domain.usecase

interface SignInUseCase {

    suspend operator fun invoke(email: String, password: String)
}