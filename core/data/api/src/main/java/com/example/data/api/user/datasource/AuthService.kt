package com.example.data.api.user.datasource

interface AuthService {

    suspend fun signIn(email: String, password: String): Boolean

    suspend fun signOut()

    suspend fun getCurrentUserId(): String

    fun isUserAuthorized(): Boolean
}