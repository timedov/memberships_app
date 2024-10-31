package com.example.domain.repository.datasource

import com.example.domain.model.PostDataDomainModel

interface PostDraftLocalDataSource {

    suspend fun getPostDraft(): PostDataDomainModel?

    suspend fun savePostDraft(post: PostDataDomainModel)

    suspend fun removePostDraft(postDraft: PostDataDomainModel)
}
