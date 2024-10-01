package com.example.network.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.common.utils.Constants
import com.example.domain.model.PostDomainModel
import com.example.domain.model.TierType
import com.example.domain.repository.PostRepository
import com.example.network.repository.paging.PostPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val postPagingSource: PostPagingSource,
) : PostRepository {

    override suspend fun getPostById(id: Long): PostDomainModel {
        TODO("Not yet implemented")
    }

    override fun getPostsByTier(tier: TierType): Flow<PagingData<PostDomainModel>> =
        Pager(
            config = PagingConfig(
                pageSize = Constants.DEFAULT_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { postPagingSource.apply { this.tier = tier } }
        ).flow

    override fun getPostsOfUser(username: String): Flow<PagingData<PostDomainModel>> =
        Pager(
            config = PagingConfig(
                pageSize = Constants.DEFAULT_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { postPagingSource.apply { author = username } }
        ).flow
}