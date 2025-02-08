package com.example.api.post.datasource

import com.example.data.api.post.model.PostDataModel


interface PostDetailsLocalDataSource {

    suspend fun getPostDetailsById(id: Long): PostDataModel

    suspend fun savePost(postData: PostDataModel)
}
