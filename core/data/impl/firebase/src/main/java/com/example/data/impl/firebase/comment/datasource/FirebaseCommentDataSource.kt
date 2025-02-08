package com.example.data.impl.firebase.comment.datasource

import com.example.common.utils.Keys
import com.example.data.api.comment.datasource.CommentDataSource
import com.example.data.api.comment.model.CommentDataModel
import com.example.data.impl.firebase.utils.toCommentDataModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

internal class FirebaseCommentDataSource @Inject constructor(
    private val firestore: FirebaseFirestore
) : CommentDataSource {

    override suspend fun addComment(comment: CommentDataModel) {
        firestore.collection(Keys.COMMENTS_COLLECTION_KEY)
            .document(comment.id)
            .set(comment)
            .await()
    }

    override suspend fun getCommentCountByPostId(postId: String): Int =
        firestore.collection(Keys.COMMENTS_COLLECTION_KEY)
            .whereEqualTo(Keys.POST_ID_KEY, postId)
            .get()
            .await()
            .size()

    override suspend fun getCommentById(id: String): CommentDataModel =
        firestore.collection(Keys.COMMENTS_COLLECTION_KEY)
            .document(id)
            .get()
            .await()
            .toCommentDataModel()

    override suspend fun getComments(
        postId: String,
        limit: Int,
        startAfter: Long?,
        startAfterId: String?
    ): List<CommentDataModel> {
        var finalQuery = firestore.collection(Keys.COMMENTS_COLLECTION_KEY)
            .whereEqualTo(Keys.POST_ID_KEY, postId)
            .orderBy(Keys.POSTED_AT_KEY, Query.Direction.ASCENDING)
            .orderBy(Keys.ID_KEY, Query.Direction.ASCENDING)
            .limit(limit.toLong())

        startAfter?.let {
            finalQuery = finalQuery.startAfter(it, startAfterId.orEmpty())
        }

        return finalQuery.get().await().documents.mapNotNull { it.toCommentDataModel() }
    }

    override suspend fun getCommentsByParentId(
        parentCommentId: String,
        limit: Int,
        startAfter: Long?,
        startAfterId: String?
    ): List<CommentDataModel> {
        var finalQuery = firestore.collection(Keys.COMMENTS_COLLECTION_KEY)
            .whereEqualTo(Keys.PARENT_COMMENT_ID_KEY, parentCommentId)
            .orderBy(Keys.POSTED_AT_KEY, Query.Direction.ASCENDING)
            .orderBy(Keys.ID_KEY, Query.Direction.ASCENDING)
            .limit(limit.toLong())

        startAfter?.let {
            finalQuery = finalQuery.startAfter(it, startAfterId.orEmpty())
        }

        return finalQuery.get().await().documents.mapNotNull { it.toCommentDataModel() }
    }
}
