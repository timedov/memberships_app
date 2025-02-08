package com.example.profile.api.domain.repository

import com.example.profile.api.domain.model.SubscribeDomainModel

interface SubscribeRepository {

    suspend fun subscribeToUser(subscribe: SubscribeDomainModel)

    suspend fun unsubscribeFromUser(subscribedTo: String, followed: String)

    suspend fun getUserSubscribersCount(username: String): Int

    suspend fun isUserSubscribed(subscribedTo: String, followed: String): Boolean
}