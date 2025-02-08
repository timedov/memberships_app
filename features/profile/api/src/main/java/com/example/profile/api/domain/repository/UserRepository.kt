package com.example.profile.api.domain.repository

import com.example.profile.api.domain.model.UserDomainModel

interface UserRepository {

    suspend fun getUserByUsername(username: String): UserDomainModel

    suspend fun getCurrentUserCredentials(): String
}