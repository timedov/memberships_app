package com.example.signin.impl.domain.usecase

import com.example.signin.api.domain.usecase.IsUserAuthorizedUseCase
import com.example.data.api.user.datasource.AuthService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class IsUserAuthorizedUseCaseImpl @Inject constructor(
    private val authService: AuthService
) : IsUserAuthorizedUseCase {

    override operator fun invoke(): Boolean = authService.isUserAuthorized()
}