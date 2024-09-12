package com.example.network.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.common.utils.Constants
import com.example.domain.model.PostModel
import com.example.domain.model.Tier
import com.example.domain.repository.PostRepository
import com.example.network.repository.paging.PostPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val postPagingSource: PostPagingSource,
) : PostRepository {

    override suspend fun getPostById(id: Long): PostModel {
        TODO("Not yet implemented")
    }

    override fun getPosts(): Flow<PagingData<PostModel>> =
        Pager(
            config = PagingConfig(
                pageSize = Constants.DEFAULT_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { postPagingSource }
        ).flow

    override suspend fun getPostsByTier(tier: Tier): Flow<PagingData<PostModel>> =
        Pager(
            config = PagingConfig(
                pageSize = Constants.DEFAULT_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { postPagingSource.apply { setTier(tier) } }
        ).flow
}