package com.example.local.comment.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.local.comment.entity.CommentEntity

@Dao
interface CommentDao {

    @Query("SELECT * FROM comments WHERE postId = :postId ORDER BY postedAt DESC")
    fun getCommentsByPostId(postId: Long): PagingSource<Int, CommentEntity>

    @Query("SELECT * FROM comments WHERE parentCommentId = :parentCommentId ORDER BY postedAt ASC")
    fun getCommentsByParentCommentId(parentCommentId: String): PagingSource<Int, CommentEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComment(comment: CommentEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(comments: List<CommentEntity>)

    @Query("DELETE FROM comments WHERE cachedAt < :expirationTime")
    suspend fun clearExpiredComments(expirationTime: Long)

}


