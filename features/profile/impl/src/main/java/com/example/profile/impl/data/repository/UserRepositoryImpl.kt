package com.example.profile.impl.data.repository

import com.example.profile.api.domain.repository.UserRepository
import com.example.profile.api.domain.model.UserDomainModel
import com.example.data.api.user.datasource.AuthService
import com.example.data.api.user.datasource.UserDataSource
import com.example.profile.impl.data.utils.toDomainModel
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource,
    private val authService: AuthService
) : UserRepository {

    override suspend fun getUserByUsername(username: String): UserDomainModel =
        userDataSource.getUserByUsername(username).toDomainModel()

    override suspend fun getCurrentUserCredentials(): String =
        userDataSource.getUserCredentials(authService.getCurrentUserId())
}