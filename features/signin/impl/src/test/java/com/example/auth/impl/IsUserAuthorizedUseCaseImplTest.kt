package com.example.auth.impl

import com.example.data.api.user.datasource.AuthService
import com.example.signin.impl.domain.usecase.IsUserAuthorizedUseCaseImpl
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertTrue
import org.junit.Test

class IsUserAuthorizedUseCaseImplTest {
    @Test
    fun `invoke returns auth status from authService`() {
        val authService = mockk<AuthService>()
        every { authService.isUserAuthorized() } returns true
        val useCase = IsUserAuthorizedUseCaseImpl(authService)
        val result = useCase.invoke()
        assertTrue(result)
    }
}
