package com.example.postdetails.api.domain.repository

import androidx.paging.PagingData
import com.example.postdetails.api.domain.model.CommentDomainModel
import kotlinx.coroutines.flow.Flow

interface CommentRepository {

    fun getCommentsByPostId(postId: String): Flow<PagingData<CommentDomainModel>>

    suspend fun addComment(comment: CommentDomainModel)

    suspend fun getCommentCountByPostId(postId: String): Int

    suspend fun getCommentById(id: String): CommentDomainModel

    fun getCommentsByParentCommentId(parentCommentId: String): Flow<PagingData<CommentDomainModel>>
}