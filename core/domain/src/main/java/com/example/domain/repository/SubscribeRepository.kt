package com.example.domain.repository

interface SubscribeRepository {

    suspend fun subscribeToTier(tierId: Long)
}