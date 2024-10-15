package com.example.domain.repository.datasource

import com.example.domain.model.PostDataDomainModel

interface PostLocalDataSource {

    suspend fun getPostById(id: Long): PostDataDomainModel?

    suspend fun savePost(post: PostDataDomainModel)
}
