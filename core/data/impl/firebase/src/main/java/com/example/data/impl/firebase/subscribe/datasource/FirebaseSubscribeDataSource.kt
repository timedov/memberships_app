package com.example.data.impl.firebase.subscribe.datasource

import android.util.Log
import com.example.data.api.subscribe.datasource.SubscribeDataSource
import com.example.data.api.subscribe.model.SubscribeDataModel
import com.example.api.subscribe.model.SubscribeStatus
import com.example.common.utils.Keys
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseSubscribeDataSource @Inject constructor(
    private val firestore: FirebaseFirestore
): SubscribeDataSource {

    override suspend fun subscribeToUser(subscribe: SubscribeDataModel) {
        firestore.collection(Keys.SUBSCRIBES_COLLECTION_KEY)
            .document(subscribe.id)
            .set(subscribe)
            .await()
    }

    override suspend fun unsubscribeFromUser(subscribedTo: String, followed: String) {
        firestore.collection(Keys.SUBSCRIBES_COLLECTION_KEY)
            .whereEqualTo(Keys.FOLLOWED_KEY, followed)
            .whereEqualTo(Keys.SUBSCRIBED_TO_KEY, subscribedTo)
            .get()
            .await()
            .forEach { it.reference.delete() }
    }

    override suspend fun getUserSubscribersCount(username: String): Int =
        firestore.collection(Keys.SUBSCRIBES_COLLECTION_KEY)
            .whereEqualTo(Keys.FOLLOWED_KEY, username)
            .get()
            .await()
            .size()

    override suspend fun isUserSubscribed(subscribedTo: String, followed: String): Boolean {
        val result = firestore.collection(Keys.SUBSCRIBES_COLLECTION_KEY)
            .whereEqualTo(Keys.FOLLOWED_KEY, followed)
            .whereEqualTo(Keys.SUBSCRIBED_TO_KEY, subscribedTo)
            .get()
            .await()

        return result.isEmpty.not()
    }
}