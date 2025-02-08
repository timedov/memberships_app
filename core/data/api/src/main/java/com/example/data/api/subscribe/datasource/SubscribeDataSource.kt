package com.example.data.api.subscribe.datasource

import com.example.data.api.subscribe.model.SubscribeDataModel

interface SubscribeDataSource {

    suspend fun subscribeToUser(subscribe: SubscribeDataModel)

    suspend fun unsubscribeFromUser(subscribedTo: String, followed: String)

    suspend fun getUserSubscribersCount(username: String): Int

    suspend fun isUserSubscribed(subscribedTo: String, followed: String): Boolean
}