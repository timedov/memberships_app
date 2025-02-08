package com.example.data.api.post.datasource

import com.example.data.api.post.model.PostDataModel

interface PostDraftLocalDataSource {

    suspend fun isPostDraftExists(): Boolean

    suspend fun getPostDraft(): PostDataModel

    suspend fun savePostDraft(postDraft: PostDataModel)

    suspend fun removePostDraft()
}
