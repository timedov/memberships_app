package com.example.domain.repository

import androidx.paging.PagingData
import com.example.domain.model.PostDataDomainModel
import com.example.domain.model.PostDomainModel
import com.example.common.model.TierType
import kotlinx.coroutines.flow.Flow
import java.io.File

interface PostRepository {

    suspend fun getPostById(id: Long): PostDataDomainModel

    fun getPostsByTier(tier: TierType): Flow<PagingData<PostDomainModel>>

    fun getPostsOfUser(username: String): Flow<PagingData<PostDomainModel>>

    suspend fun savePost(
        post: PostDataDomainModel,
        content: File?,
        mimeType: String?,
        username: String
    )

    suspend fun savePostDraft(post: PostDataDomainModel)

    suspend fun getPostDraft(): PostDataDomainModel

    suspend fun removePostDraft()
}
