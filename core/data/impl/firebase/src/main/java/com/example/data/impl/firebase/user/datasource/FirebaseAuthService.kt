package com.example.data.impl.firebase.user.datasource

import com.example.data.api.user.datasource.AuthService
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseAuthService @Inject constructor(
    private val auth: FirebaseAuth
) : AuthService {

    override suspend fun signIn(email: String, password: String): Boolean {
        val userFB = auth.signInWithEmailAndPassword(email, password).await()
        return userFB.user != null
    }

    override suspend fun signOut() {
        auth.signOut()
    }

    override suspend fun getCurrentUserId(): String =
        requireNotNull(auth.currentUser?.uid)

    override fun isUserAuthorized(): Boolean = auth.currentUser != null
}