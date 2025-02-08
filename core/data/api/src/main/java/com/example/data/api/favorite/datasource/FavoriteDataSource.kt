package com.example.data.api.favorite.datasource

import com.example.data.api.favorite.model.FavoriteDataModel

interface FavoriteDataSource {

    suspend fun setPostFavorite(favorite: FavoriteDataModel)

    suspend fun getFavoriteCountByPostId(postId: String): Int

    suspend fun isPostFavorite(postId: String, username: String): Boolean
}