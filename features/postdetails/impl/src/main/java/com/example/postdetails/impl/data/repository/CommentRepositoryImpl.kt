package com.example.postdetails.impl.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.data.api.comment.datasource.CommentDataSource
import com.example.postdetails.api.domain.model.CommentDomainModel
import com.example.postdetails.api.domain.repository.CommentRepository
import com.example.common.utils.Constants
import com.example.postdetails.impl.data.repository.pagingsource.CommentPagingSource
import com.example.postdetails.impl.data.repository.pagingsource.CommentReplyPagingSource
import com.example.postdetails.impl.data.utils.toDataModel
import com.example.postdetails.impl.data.utils.toDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CommentRepositoryImpl @Inject constructor(
    private val commentDataSource: CommentDataSource,
) : CommentRepository {

    override fun getCommentsByPostId(postId: String): Flow<PagingData<CommentDomainModel>> =
        Pager(
            config = PagingConfig(pageSize = Constants.DEFAULT_PAGE_SIZE),
            pagingSourceFactory = {
                CommentPagingSource(
                    postId = postId,
                    commentDataSource = commentDataSource,
                )
            }
        ).flow.map { pagingData ->
            pagingData.map { it.toDomainModel() }
        }

    override suspend fun addComment(comment: CommentDomainModel) {
        commentDataSource.addComment(comment.toDataModel())
    }

    override suspend fun getCommentCountByPostId(postId: String) =
        commentDataSource.getCommentCountByPostId(postId)

    override suspend fun getCommentById(id: String): CommentDomainModel =
        commentDataSource.getCommentById(id).toDomainModel()

    override fun getCommentsByParentCommentId(
        parentCommentId: String
    ): Flow<PagingData<CommentDomainModel>> =
        Pager(
            config = PagingConfig(pageSize = Constants.DEFAULT_PAGE_SIZE),
            pagingSourceFactory = {
                CommentReplyPagingSource(
                    parentCommentId = parentCommentId,
                    commentDataSource = commentDataSource,
                )
            }
        ).flow.map { pagingData ->
            pagingData.map { it.toDomainModel() }
        }
}