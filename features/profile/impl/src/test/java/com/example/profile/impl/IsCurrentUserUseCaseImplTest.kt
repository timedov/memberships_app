package com.example.profile.impl

import com.example.profile.api.domain.repository.UserRepository
import com.example.profile.impl.domain.usecase.IsCurrentUserUseCaseImpl
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Assert.assertFalse
import org.junit.Test

class IsCurrentUserUseCaseImplTest {
    @Test
    fun `invoke returns true when username matches current user`() = runBlocking {
        val userRepository = mockk<UserRepository>()
        val currentUsername = "testUser"
        coEvery { userRepository.getCurrentUserCredentials() } returns currentUsername
        val useCase = IsCurrentUserUseCaseImpl(userRepository, Dispatchers.Unconfined)
        val result = useCase.invoke(currentUsername)
        assertTrue(result)
        coVerify(exactly = 1) { userRepository.getCurrentUserCredentials() }
    }

    @Test
    fun `invoke returns false when username does not match current user`() = runBlocking {
        val userRepository = mockk<UserRepository>()
        coEvery { userRepository.getCurrentUserCredentials() } returns "anotherUser"
        val useCase = IsCurrentUserUseCaseImpl(userRepository, Dispatchers.Unconfined)
        val result = useCase.invoke("testUser")
        assertFalse(result)
        coVerify(exactly = 1) { userRepository.getCurrentUserCredentials() }
    }
}
