package com.example.data.api.user.datasource

import com.example.data.api.user.model.UserDataModel

interface UserDataSource {

    suspend fun getUserByUsername(username: String): UserDataModel

    suspend fun getUserCredentials(userId: String): String

}