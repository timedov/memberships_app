package com.example.feed.impl.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.data.api.post.datasource.PostDataSource
import com.example.data.api.post.datasource.PostDraftLocalDataSource
import com.example.feed.api.domain.model.PostDomainModel
import com.example.feed.api.domain.repository.PostRepository
import com.example.common.utils.Constants
import com.example.feed.api.domain.model.SavePostForm
import com.example.feed.impl.data.repository.pagingsource.PostsByUserPagingSource
import com.example.feed.impl.data.repository.pagingsource.PostsPagingSource
import com.example.feed.impl.data.utils.toDataModel
import com.example.feed.impl.data.utils.toDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val postDataSource: PostDataSource,
    private val postDraftLocalDataSource: PostDraftLocalDataSource
): PostRepository {

    override fun getPosts(): Flow<PagingData<PostDomainModel>> =
        Pager(
            config = PagingConfig(
                pageSize = Constants.DEFAULT_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                PostsPagingSource(postDataSource = postDataSource)
            }
        ).flow
            .map { pagingData ->
                pagingData.map {
                    it.toDomainModel()
                }
            }

    override fun getPostsOfUser(username: String): Flow<PagingData<PostDomainModel>> =
        Pager(
            config = PagingConfig(
                pageSize = Constants.DEFAULT_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                PostsByUserPagingSource(
                    username = username,
                    postDataSource = postDataSource
                )
            }
        ).flow
            .map { pagingData ->
                pagingData.map {
                    it.toDomainModel()
                }
            }

    override suspend fun getPostById(id: String): PostDomainModel =
        postDataSource.getPostById(id).toDomainModel()

    override suspend fun savePost(post: SavePostForm) {
        postDataSource.savePost(post.toDataModel())
    }

    override suspend fun savePostDraft(postDraft: SavePostForm) {
        postDraftLocalDataSource.savePostDraft(postDraft.toDataModel())
    }

    override suspend fun getPostDraft(): PostDomainModel =
        postDraftLocalDataSource.getPostDraft().toDomainModel()

    override suspend fun hasPostDraft(): Boolean =
        postDraftLocalDataSource.isPostDraftExists()

    override suspend fun removePostDraft() {
        postDraftLocalDataSource.removePostDraft()
    }
}
