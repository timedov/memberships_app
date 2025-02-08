package com.example.postdetails.impl.data.repository

import com.example.postdetails.api.domain.model.FavoriteDomainModel
import com.example.postdetails.api.domain.repository.FavoriteRepository
import com.example.data.api.favorite.datasource.FavoriteDataSource
import com.example.postdetails.impl.data.utils.toDataModel
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val favoriteDataSource: FavoriteDataSource
) : FavoriteRepository {
    override suspend fun setPostFavorite(favorite: FavoriteDomainModel) {
        favoriteDataSource.setPostFavorite(favorite.toDataModel())
    }

    override suspend fun getFavoriteCountByPostId(postId: String): Int =
        favoriteDataSource.getFavoriteCountByPostId(postId)


    override suspend fun isPostFavorite(postId: String, username: String): Boolean =
        favoriteDataSource.isPostFavorite(postId, username)
}
