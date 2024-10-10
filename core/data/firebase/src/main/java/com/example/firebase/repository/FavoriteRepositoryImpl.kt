package com.example.firebase.repository

import com.example.common.utils.Keys
import com.example.domain.model.FavoriteDomainModel
import com.example.domain.repository.FavoriteRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val db: FirebaseFirestore
) : FavoriteRepository {
    override suspend fun setPostFavorite(model: FavoriteDomainModel) {
        if (model.isFavorite) {
            db.collection(Keys.FAVORITES_COLLECTION_KEY)
                .document(model.id)
                .set(model)
                .await()
        } else {
            db.collection(Keys.FAVORITES_COLLECTION_KEY)
                .document(model.id)
                .delete()
                .await()
        }
    }

    override suspend fun getFavoriteCountByPostId(postId: Long): Int =
        db.collection(Keys.FAVORITES_COLLECTION_KEY)
            .whereEqualTo(Keys.POST_ID_KEY, postId)
            .get()
            .await()
            .size()


    override suspend fun isPostFavorite(postId: Long, username: String): Boolean =
        db.collection(Keys.FAVORITES_COLLECTION_KEY)
            .whereEqualTo(Keys.POST_ID_KEY, postId)
            .whereEqualTo(Keys.USERNAME_KEY, username)
            .get()
            .await()
            .documents
            .isNotEmpty()
}
