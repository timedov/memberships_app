package com.example.data.api.comment.datasource

import com.example.data.api.comment.model.CommentDataModel

interface CommentDataSource {

    suspend fun addComment(comment: CommentDataModel)

    suspend fun getCommentCountByPostId(postId: String): Int

    suspend fun getCommentById(id: String): CommentDataModel

    suspend fun getComments(
        postId: String,
        limit: Int,
        startAfter: Long? = null,
        startAfterId: String? = null
    ): List<CommentDataModel>

    suspend fun getCommentsByParentId(
        parentCommentId: String,
        limit: Int,
        startAfter: Long? = null,
        startAfterId: String? = null
    ): List<CommentDataModel>
}