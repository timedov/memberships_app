package com.example.network.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.common.utils.Constants
import com.example.domain.model.PostDataDomainModel
import com.example.domain.model.PostDomainModel
import com.example.common.model.TierType
import com.example.domain.repository.PostRepository
import com.example.domain.repository.datasource.PostDraftLocalDataSource
import com.example.domain.repository.datasource.PostLocalDataSource
import com.example.network.mapper.PostDomainModelMapper
import com.example.network.remote.datasource.PostApi
import com.example.network.repository.paging.PostPagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val postApi: PostApi,
    private val postPagingSource: PostPagingSource,
    private val postLocalDataSource: PostLocalDataSource,
    private val postDraftLocalDataSource: PostDraftLocalDataSource,
    private val postDomainModelMapper: PostDomainModelMapper
) : PostRepository {

    override suspend fun getPostById(id: Long): PostDataDomainModel {
        val cachedPost = postLocalDataSource.getPostDataById(id)
        if (cachedPost != null) {
            return cachedPost
        }

        val postDataResponse = postApi.getPostById(id)
        val domainModel = postDomainModelMapper.mapDataResponseToDataDomainModel(postDataResponse)

        postLocalDataSource.savePostData(domainModel)

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

    override suspend fun savePost(
        post: PostDataDomainModel,
        content: File?,
        mimeType: String?,
        username: String
    ) {
        val postDataJson =
            Json.encodeToString(postDomainModelMapper.mapDataDomainModelToRequest(post, username))
        val postDataRequestBody =
            postDataJson.toRequestBody(Constants.JSON_CONTENT_TYPE.toMediaType())

        val contentPart = content?.let {
            val requestFile = it.asRequestBody(mimeType?.toMediaTypeOrNull())
            MultipartBody.Part.createFormData("content", it.name, requestFile)
        }

        postApi.savePost(
            postData = postDataRequestBody,
            content = contentPart
        )
    }

    override suspend fun savePostDraft(post: PostDataDomainModel) {
        postDraftLocalDataSource.savePostDraft(post)
    }

    override suspend fun getPostDraft(): PostDataDomainModel =
        postDraftLocalDataSource.getPostDraft() ?: PostDataDomainModel()

    override suspend fun removePostDraft(postDraft: PostDataDomainModel) {
        postDraftLocalDataSource.removePostDraft(postDraft)
    }
}