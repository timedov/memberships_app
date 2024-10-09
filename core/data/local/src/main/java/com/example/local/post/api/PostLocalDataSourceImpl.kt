package com.example.local.post.api

import com.example.domain.model.PostDataDomainModel
import com.example.domain.service.PostLocalDataSource
import com.example.local.post.dao.PostDao
import com.example.local.utils.toDomainModel
import com.example.local.utils.toEntity
import javax.inject.Inject

class PostLocalDataSourceImpl @Inject constructor(
    private val postDao: PostDao
) : PostLocalDataSource {

    override suspend fun getPostById(id: Long): PostDataDomainModel? {
        return postDao.getPostById(id)?.toDomainModel()
    }

    override suspend fun savePost(post: PostDataDomainModel) {
        postDao.insertPost(post.toEntity())
    }
}