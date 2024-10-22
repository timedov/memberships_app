package com.example.domain.repository.datasource

import com.example.domain.model.PostDataDomainModel

interface PostLocalDataSource {

    suspend fun getPostDataById(id: Long): PostDataDomainModel?

    suspend fun savePostData(postData: PostDataDomainModel)
}
