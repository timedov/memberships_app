package com.example.domain.repository

import com.example.domain.model.TierDomainModel

interface TierRepository {

    suspend fun getTiersByUser(username: String): List<TierDomainModel>

    suspend fun getTierById(id: Long): TierDomainModel?

    suspend fun saveTier(tier: TierDomainModel, username: String)

    suspend fun deleteTier(tier: TierDomainModel)
}