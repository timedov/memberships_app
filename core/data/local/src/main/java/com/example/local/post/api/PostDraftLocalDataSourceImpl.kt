package com.example.local.post.api

import com.example.domain.model.PostDataDomainModel
import com.example.domain.repository.datasource.PostDraftLocalDataSource
import com.example.local.post.dao.PostDraftDao
import com.example.local.utils.toDomainModel
import com.example.local.utils.toDraftEntity
import javax.inject.Inject

class PostDraftLocalDataSourceImpl @Inject constructor(
    private val postDraftDao: PostDraftDao
) : PostDraftLocalDataSource {

    override suspend fun getPostDraft(): PostDataDomainModel? =
        postDraftDao.getPostDraft()?.toDomainModel()

    override suspend fun savePostDraft(post: PostDataDomainModel) {
        postDraftDao.insertPostDraft(postDraft = post.toDraftEntity())
    }

    override suspend fun removePostDraft(postDraft: PostDataDomainModel) {
        postDraftDao.removePostDraft(postDraft = postDraft.toDraftEntity())
    }
}
