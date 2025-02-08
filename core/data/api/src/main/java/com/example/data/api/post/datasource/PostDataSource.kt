package com.example.data.api.post.datasource

import com.example.data.api.post.model.PostDataModel

interface PostDataSource {

    suspend fun getPosts(
        limit: Int,
        startAfter: Long? = null,
        startAfterId: String? = null
    ): List<PostDataModel>

    suspend fun getPostsByAuthor(
        authorName: String,
        limit: Int,
        startAfter: Long? = null,
        startAfterId: String? = null
    ): List<PostDataModel>

    suspend fun getPostById(id: String): PostDataModel

    suspend fun savePost(post: PostDataModel)
}