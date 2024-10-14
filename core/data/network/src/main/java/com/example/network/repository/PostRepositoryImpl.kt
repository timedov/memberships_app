package com.example.network.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.common.utils.Constants
import com.example.domain.model.PostDataDomainModel
import com.example.domain.model.PostDomainModel
import com.example.domain.model.TierType
import com.example.domain.repository.PostRepository
import com.example.domain.repository.datasource.PostLocalDataSource
import com.example.network.mapper.PostDomainModelMapper
import com.example.network.remote.datasource.PostApi
import com.example.network.repository.paging.PostPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val postApi: PostApi,
    private val postPagingSource: PostPagingSource,
    private val postLocalDataSource: PostLocalDataSource,
    private val postDomainModelMapper: PostDomainModelMapper
) : PostRepository {

    override suspend fun getPostById(id: Long): PostDataDomainModel {
        val cachedPost = postLocalDataSource.getPostById(id)
        if (cachedPost != null) {
            return cachedPost
        }

        val postDataResponse = postApi.getPostById(id)
        val domainModel = postDomainModelMapper.mapDataResponseToDataDomainModel(postDataResponse)

        postLocalDataSource.savePost(domainModel)

        return domainModel
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