package com.example.signin.impl.domain.usecase

import com.example.signin.api.domain.usecase.SignInUseCase
import com.example.data.api.user.datasource.AuthService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SignInUseCaseImpl @Inject constructor(
    private val authService: AuthService,
    private val coroutineDispatcher: CoroutineDispatcher
) : SignInUseCase {

    override suspend fun invoke(email: String, password: String) {
        withContext(coroutineDispatcher) {
            authService.signIn(email, password)
        }
    }
}