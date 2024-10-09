package com.example.domain.service

import com.example.domain.model.PostDataDomainModel

interface PostLocalDataSource {

    suspend fun getPostById(id: Long): PostDataDomainModel?

    suspend fun savePost(post: PostDataDomainModel)
}
