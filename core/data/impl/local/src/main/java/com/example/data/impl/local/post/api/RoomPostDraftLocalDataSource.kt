package com.example.data.impl.local.post.api

import com.example.data.api.post.datasource.PostDraftLocalDataSource
import com.example.data.api.post.model.PostDataModel
import com.example.data.impl.local.post.dao.PostDraftDao
import com.example.data.impl.local.utils.toDomainModel
import com.example.data.impl.local.utils.toDraftEntity
import javax.inject.Inject

class RoomPostDraftLocalDataSource @Inject constructor(
    private val postDraftDao: PostDraftDao
) : PostDraftLocalDataSource {

    override suspend fun isPostDraftExists(): Boolean =
        postDraftDao.isPostDraftExists()

    override suspend fun getPostDraft(): PostDataModel =
        postDraftDao.getPostDraft()?.toDomainModel() ?: PostDataModel()

    override suspend fun savePostDraft(postDraft: PostDataModel) {
        postDraftDao.insertPostDraft(postDraft = postDraft.toDraftEntity())
    }

    override suspend fun removePostDraft() {
        postDraftDao.removePostDraft()
    }
}
