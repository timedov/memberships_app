package com.example.domain.repository

import androidx.paging.PagingData
import com.example.domain.model.CommentDomainModel
import kotlinx.coroutines.flow.Flow

interface CommentRepository {

    fun getCommentsByPostId(postId: Long): Flow<PagingData<CommentDomainModel>>

    suspend fun addComment(postId: Long, comment: CommentDomainModel)

    suspend fun getCommentCountByPostId(postId: Long): Int
}