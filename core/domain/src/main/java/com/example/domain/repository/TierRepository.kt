package com.example.domain.repository

import com.example.domain.model.TierModel

interface TierRepository {

    fun getTiersByUser(username: String): List<TierModel>

    fun getTierById(id: Int): TierModel

    fun saveTier(tier: TierModel): Boolean

    fun deleteTier(tier: TierModel): Boolean
}