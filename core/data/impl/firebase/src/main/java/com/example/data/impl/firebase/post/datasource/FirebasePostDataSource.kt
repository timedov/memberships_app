package com.example.data.impl.firebase.post.datasource

import com.example.common.utils.Keys
import com.example.data.api.post.datasource.PostDataSource
import com.example.data.api.post.model.PostDataModel
import com.example.data.impl.firebase.utils.toPostDataModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

internal class FirebasePostDataSource @Inject constructor(
    private val firestore: FirebaseFirestore
) : PostDataSource {

    override suspend fun getPosts(
        limit: Int,
        startAfter: Long?,
        startAfterId: String?
    ): List<PostDataModel> =
        fetchPosts(
            query = firestore.collection(Keys.POSTS_COLLECTION_KEY),
            limit,
            startAfter,
            startAfterId
        )

    override suspend fun getPostsByAuthor(
        authorName: String,
        limit: Int,
        startAfter: Long?,
        startAfterId: String?
    ): List<PostDataModel> {
        return fetchPosts(
            query = firestore.collection(Keys.POSTS_COLLECTION_KEY)
                .whereEqualTo(Keys.AUTHOR_KEY, authorName),
            limit,
            startAfter,
            startAfterId
        )
    }

    override suspend fun getPostById(id: String): PostDataModel =
        firestore.collection(Keys.POSTS_COLLECTION_KEY)
            .document(id)
            .get()
            .await()
            .toPostDataModel()

    override suspend fun savePost(post: PostDataModel) {
        firestore.collection(Keys.POSTS_COLLECTION_KEY)
            .document(post.id)
            .set(post)
            .await()
    }

    private suspend fun fetchPosts(
        query: Query,
        limit: Int,
        startAfter: Long?,
        startAfterId: String?
    ): List<PostDataModel> {
        var finalQuery = query
            .orderBy(Keys.POSTED_AT_KEY, Query.Direction.DESCENDING)
            .orderBy(Keys.ID_KEY, Query.Direction.ASCENDING)
            .limit(limit.toLong())

        startAfter?.let { finalQuery = finalQuery.startAfter(startAfter, startAfterId.orEmpty()) }

        return finalQuery.get().await().documents.mapNotNull { it.toPostDataModel() }
    }
}
