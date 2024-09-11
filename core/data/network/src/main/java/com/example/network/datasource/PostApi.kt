package com.example.network.datasource

import com.example.network.datasource.responses.PostResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PostApi {

    @GET("posts")
    suspend fun getPosts(@Query("page") page: Int): List<PostResponse>
}