package com.example.domain.repository

import androidx.paging.PagingData
import com.example.domain.model.PostModel
import com.example.domain.model.Tier
import kotlinx.coroutines.flow.Flow

interface PostRepository {

    suspend fun getPostById(id: Long): PostModel

    fun getPosts(): Flow<PagingData<PostModel>>

    suspend fun getPostsByTier(tier: Tier): Flow<PagingData<PostModel>>

    fun getPostsOfUser(username: String): Flow<PagingData<PostModel>>
}