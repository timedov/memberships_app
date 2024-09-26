package com.example.domain.repository

import com.example.domain.model.UserDetailsModel
import com.example.domain.model.UserDomainModel

interface UserRepository {
    suspend fun signUp(
        username: String,
        email: String,
        password: String
    ): UserDomainModel

    suspend fun signIn(email: String, password: String): UserDomainModel

    suspend fun getUserById(userId: String): UserDomainModel

    suspend fun getCurrentUserId(): String?

    suspend fun getCurrentUserCredentials(): String

    suspend fun updateUserCredentials(username: String) : Boolean

    suspend fun getUserDetailsByUsername(username: String): UserDetailsModel

    suspend fun signOut()
}