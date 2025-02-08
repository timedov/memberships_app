package com.example.data.impl.firebase.favorite.datasource

import com.example.data.api.favorite.datasource.FavoriteDataSource
import com.example.data.api.favorite.model.FavoriteDataModel
import com.example.common.utils.Keys
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

internal class FirebaseFavoriteDataSource @Inject constructor(
    private val firestore: FirebaseFirestore
): FavoriteDataSource {

    override suspend fun setPostFavorite(favorite: FavoriteDataModel) {
        if (favorite.isFavorite) {
            firestore.collection(Keys.FAVORITES_COLLECTION_KEY)
                .document(favorite.id)
                .set(favorite)
                .await()
        } else {
            firestore.collection(Keys.FAVORITES_COLLECTION_KEY)
                .whereEqualTo(Keys.POST_ID_KEY, favorite.postId)
                .whereEqualTo(Keys.USERNAME_KEY, favorite.username)
                .get()
                .await()
                .documents
                .firstOrNull()
                ?.reference
                ?.delete()
                ?.await()
        }
    }

    override suspend fun getFavoriteCountByPostId(postId: String): Int =
        firestore.collection(Keys.FAVORITES_COLLECTION_KEY)
            .whereEqualTo(Keys.POST_ID_KEY, postId)
            .get()
            .await()
            .size()

    override suspend fun isPostFavorite(postId: String, username: String): Boolean =
        firestore.collection(Keys.FAVORITES_COLLECTION_KEY)
            .whereEqualTo(Keys.POST_ID_KEY, postId)
            .whereEqualTo(Keys.USERNAME_KEY, username)
            .get()
            .await()
            .documents
            .isNotEmpty()
}
