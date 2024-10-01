package com.example.domain.repository

import androidx.paging.PagingData
import com.example.domain.model.PostDomainModel
import com.example.domain.model.TierType
import kotlinx.coroutines.flow.Flow

interface PostRepository {

    suspend fun getPostById(id: Long): PostDomainModel

    suspend fun getPostsByTier(tier: TierType): Flow<PagingData<PostDomainModel>>
}