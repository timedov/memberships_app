package com.example.firebase.repository

import com.example.common.utils.Keys
import com.example.domain.model.SubscribeDomainModel
import com.example.domain.model.SubscribeStatus
import com.example.domain.repository.SubscribeRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class SubscribeRepositoryImpl @Inject constructor(
    private val db: FirebaseFirestore
) : SubscribeRepository {

    override suspend fun subscribeToTier(subscribeDomainModel: SubscribeDomainModel) {
        db.collection(Keys.SUBSCRIBES_COLLECTION_KEY)
            .document(subscribeDomainModel.id)
            .set(subscribeDomainModel)
            .await()
    }

    override suspend fun getUserSubscribersCount(username: String): Int =
        db.collection(Keys.SUBSCRIBES_COLLECTION_KEY)
            .whereEqualTo(Keys.SUBSCRIBED_TO_KEY, username)
            .whereEqualTo(Keys.STATUS_KEY, SubscribeStatus.ACTIVE)
            .get()
            .await()
            .size()
}