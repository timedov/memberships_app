package com.example.profile.impl

import com.example.profile.api.domain.repository.UserRepository
import com.example.profile.impl.domain.usecase.GetCurrentUsernameUseCaseImpl
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class GetCurrentUsernameUseCaseImplTest {
    @Test
    fun `invoke returns current username from repository`() = runBlocking {
        val userRepository = mockk<UserRepository>()
        val expectedUsername = "testUser"
        coEvery { userRepository.getCurrentUserCredentials() } returns expectedUsername
        val useCase = GetCurrentUsernameUseCaseImpl(userRepository, Dispatchers.Unconfined)
        val result = useCase.invoke()
        assertEquals(expectedUsername, result)
        coVerify(exactly = 1) { userRepository.getCurrentUserCredentials() }
    }
}
