package com.example.domain.repository

import com.example.domain.model.FavoriteDomainModel

interface FavoriteRepository {

    suspend fun setPostFavorite(model: FavoriteDomainModel)

    suspend fun getFavoriteCountByPostId(postId: Long): Int

    suspend fun isPostFavorite(postId: Long, username: String): Boolean
}
