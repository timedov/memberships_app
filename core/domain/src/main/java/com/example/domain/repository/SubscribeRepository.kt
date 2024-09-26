package com.example.domain.repository

import com.example.domain.model.SubscribeModel

interface SubscribeRepository {

    suspend fun subscribeToTier(subscribeModel: SubscribeModel)

    suspend fun getUserSubscribersCount(username: String): Int
}