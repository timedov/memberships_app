package com.example.feed.api.domain.repository

import androidx.paging.PagingData
import com.example.feed.api.domain.model.PostDomainModel
import com.example.feed.api.domain.model.SavePostForm
import kotlinx.coroutines.flow.Flow

interface PostRepository {

    fun getPosts(): Flow<PagingData<PostDomainModel>>

    fun getPostsOfUser(username: String): Flow<PagingData<PostDomainModel>>

    suspend fun getPostById(id: String): PostDomainModel

    suspend fun savePost(post: SavePostForm)

    suspend fun savePostDraft(postDraft: SavePostForm)

    suspend fun getPostDraft(): PostDomainModel

    suspend fun hasPostDraft(): Boolean

    suspend fun removePostDraft()
}