package com.example.api.comment.datasource

import com.example.data.api.comment.model.CommentDataModel

interface CommentLocalDataSource {

    suspend fun insertCommentList(comments: List<CommentDataModel>)

    suspend fun clearExpiredComments(expirationTime: Long)

    suspend fun insertComment(comment: CommentDataModel)
}
