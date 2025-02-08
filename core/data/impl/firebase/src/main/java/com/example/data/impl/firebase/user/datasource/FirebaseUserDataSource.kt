package com.example.data.impl.firebase.user.datasource

import com.example.data.api.user.datasource.UserDataSource
import com.example.data.api.user.model.UserDataModel
import com.example.common.utils.Keys
import com.example.data.impl.firebase.utils.toUserDataModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

internal class FirebaseUserDataSource @Inject constructor(
    private val firestore: FirebaseFirestore
): UserDataSource {

    override suspend fun getUserByUsername(username: String): UserDataModel =
        firestore.collection(Keys.USERS_COLLECTION_KEY)
            .whereEqualTo(Keys.USERNAME_KEY, username)
            .get()
            .await()
            .documents
            .first()
            .toUserDataModel()

    override suspend fun getUserCredentials(userId: String): String =
        firestore.collection(Keys.USERS_COLLECTION_KEY)
            .document(userId)
            .get()
            .await()
            .get(Keys.USERNAME_KEY) as String
}
