package com.example.local.post.api

import com.example.domain.model.PostDataDomainModel
import com.example.domain.repository.datasource.PostLocalDataSource
import com.example.local.post.dao.PostDataDao
import com.example.local.utils.toDomainModel
import com.example.local.utils.toEntity
import javax.inject.Inject

class PostLocalDataSourceImpl @Inject constructor(
    private val postDataDao: PostDataDao
) : PostLocalDataSource {

    override suspend fun getPostDataById(id: Long): PostDataDomainModel? {
        return postDataDao.getPostDataById(id)?.toDomainModel()
    }

    override suspend fun savePostData(postData: PostDataDomainModel) {
        postDataDao.insertPostData(postData.toEntity())
    }
}