package com.example.auth.impl

import com.example.data.api.user.datasource.AuthService
import com.example.signin.impl.domain.usecase.SignInUseCaseImpl
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Test

class SignInUseCaseImplTest {
    @Test
    fun `invoke calls signIn on authService`() = runBlocking {
        val authService = mockk<AuthService>(relaxed = true)
        val useCase = SignInUseCaseImpl(authService, Dispatchers.Unconfined)
        val email = "test@example.com"
        val password = "password"
        useCase.invoke(email, password)
        coVerify(exactly = 1) { authService.signIn(email, password) }
    }
}
