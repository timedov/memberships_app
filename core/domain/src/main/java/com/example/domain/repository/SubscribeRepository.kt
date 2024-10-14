package com.example.domain.repository

import com.example.domain.model.SubscribeDomainModel

interface SubscribeRepository {

    suspend fun subscribeToTier(subscribeDomainModel: SubscribeDomainModel)

    suspend fun getUserSubscribersCount(username: String): Int

    suspend fun isUserSubscribed(followed: String, subscribedAt: String): Boolean
}