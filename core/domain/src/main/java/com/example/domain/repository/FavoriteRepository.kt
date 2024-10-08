package com.example.domain.repository

interface FavoriteRepository {

    suspend fun setPostFavorite(
        username: String,
        postId: Long,
        isFavorite: Boolean
    )

    suspend fun getFavoriteCountByPostId(postId: Long): Int

    suspend fun isPostFavorite(postId: Long, username: String): Boolean
}
