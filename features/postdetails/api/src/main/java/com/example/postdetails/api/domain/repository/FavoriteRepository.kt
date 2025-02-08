package com.example.postdetails.api.domain.repository

import com.example.postdetails.api.domain.model.FavoriteDomainModel

interface FavoriteRepository {

    suspend fun setPostFavorite(favorite: FavoriteDomainModel)

    suspend fun getFavoriteCountByPostId(postId: String): Int

    suspend fun isPostFavorite(postId: String, username: String): Boolean
}
